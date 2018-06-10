package com.worldpay.sdk.wpg.integration.threeds;

import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.domain.card.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.shopper.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.request.threeds.SubmitThreeDSRequest;
import static org.hamcrest.core.Is.*;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SubmitThreeDSRequestTest extends BaseIntegrationTest
{
    private static final Logger LOG = Logger.getLogger(SubmitThreeDSRequestTest.class.getName());

    private SessionContext sessionContext;
    private OrderDetails orderDetails;
    private PaymentResponse initialPaymentResponse;

    @Before
    public void setupCardPayment() throws Exception
    {
        sessionContext = new SessionContext();
        orderDetails = new OrderDetails("threeds test order", new Amount(Currency.GBP, 2L, 1000L));

        // Send initial card payment
        CardDetails cardDetails = new CardDetails("4444333322221129", 1L, 2030L, "3D");
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("accepts", "user agent"));
        CardPaymentRequest initialRequest = new CardPaymentRequest(orderDetails, cardDetails, shopper);
        initialPaymentResponse = initialRequest.send(GATEWAY_CONTEXT, sessionContext);

        // Check threeds authentication is required
        assertNotNull(initialPaymentResponse.getThreeDsDetails());
    }

    @Test
    public void sendRubbish() throws Exception
    {
        try
        {
            // When
            SubmitThreeDSRequest request = new SubmitThreeDSRequest(orderDetails, "rubbish pa response");
            request.send(GATEWAY_CONTEXT, sessionContext);
        }
        catch (WpgErrorResponseException e)
        {
            // Then
            assertThat(e.getGatewayErrorCode(), is(7L));
            assertThat(e.getGatewayErrorMessage(), is("verification of PaRes failed"));
        }
    }

    @Test
    public void sendLegit() throws Exception
    {
        // Given
        String cookie = getInitialIssuerPage();
        String paResponse = getPaResponseFromIssuerPage(cookie);

        // When
        PaymentResponse paymentResponse = new SubmitThreeDSRequest(orderDetails, paResponse)
                .send(GATEWAY_CONTEXT, sessionContext);

        // Then
        assertNull("Not expecting threeds response", paymentResponse.getThreeDsDetails());
        assertNotNull("Expecting payment response", paymentResponse.getPayment());
        assertThat(paymentResponse.getPayment().getLastEvent(), is(LastEvent.AUTHORISED));
    }

    private String getInitialIssuerPage() throws Exception
    {
        // Make request to simulator
        String issuerUrl = initialPaymentResponse.getThreeDsDetails().getIssuerURL();
        HttpURLConnection conn = (HttpURLConnection) new URL(issuerUrl).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);

        // Write post response
        String paReq = URLEncoder.encode(initialPaymentResponse.getThreeDsDetails().getPaRequest(), "UTF-8");
        String postData = "PaReq=" + paReq + "&TermUrl=http://localhost:3000&MD=";
        byte[] postDataBytes = postData.getBytes("UTF-8");

        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.write(postDataBytes);
        dos.flush();
        dos.close();

        // Check response status code
        int statusCode = conn.getResponseCode();
        assertEquals("Unexpected response code for initial request to 3ds issuer simulator", 200, statusCode);

        // Extract required values from 3ds issuer simulator
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
        {
            buffer.append(line);
        }
        br.close();

        // Output response for debugging
        String response = buffer.toString();
        LOG.fine("3ds initial sim response: " + response);

        // Fetch cookie and make request again
        List<String> cookieList = conn.getHeaderFields().get("Set-Cookie");
        String cookie = cookieList.stream().collect(Collectors.joining(";"));
        return cookie;
    }

    private String getPaResponseFromIssuerPage(String cookie) throws Exception
    {
        // Make request to simulator submit page
        final String issuerSubmitPage = "https://secure-test.worldpay.com/servlet/GenerateThreeDSParesServlet";
        HttpURLConnection conn = (HttpURLConnection) new URL(issuerSubmitPage).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("Cookie", cookie);

        // Write post response
        String paReq = URLEncoder.encode(initialPaymentResponse.getThreeDsDetails().getPaRequest(), "UTF-8");
        String postData = "paResMagicValues=IDENTIFIED&parequest=" + paReq + "&termUrl=http://localhost:3000&MD=";
        byte[] postDataBytes = postData.getBytes("UTF-8");

        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.write(postDataBytes);
        dos.flush();
        dos.close();

        // Check response status code
        int statusCode = conn.getResponseCode();
        assertEquals("Unexpected response code for initial request to 3ds issuer simulator", 200, statusCode);

        // Extract required values from 3ds issuer simulator
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
        {
            buffer.append(line);
        }
        br.close();

        // Output response for debugging
        String response = buffer.toString();
        LOG.fine("3ds pares sim response: " + response);

        // Extract pares
        Matcher matcher = Pattern.compile("(?:.+)<input name=\"PaRes\" type=\"hidden\" value=\"([^\"]+)\"\\s+/>(?:.+)").matcher(response);
        assertTrue("PaRes not found in response", matcher.find());

        String paRes = matcher.group(1);
        return paRes;
    }

}
