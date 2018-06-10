package com.worldpay.sdk.wpg.integration;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.card.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.shopper.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.request.inquiry.OrderInquiryRequest;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BaseIntegrationTest
{
    private static final Logger LOG = Logger.getLogger(BaseIntegrationTest.class.getName());

    /**
     * Maximum number of times to poll an order for its status / last event to change.
     *
     * This is only required due to replication or/and processing delay.
     */
    protected static final int ORDER_INQUIRY_ATTEMPTS = 40;

    /**
     * Milliseconds delay between order inquiry polling attempts.
     */
    protected static final int ORDER_INQUIRY_DELAY = 2500;

    protected static final String USER;
    protected static final String PASS;
    protected static final String MERCHANT_CODE;
    protected static final String INSTALLATION_ID;
    protected static final GatewayContext GATEWAY_CONTEXT;

    static
    {
        USER = System.getProperty("sdk.user");
        PASS = System.getProperty("sdk.pass");
        MERCHANT_CODE = System.getProperty("sdk.merchantCode");
        INSTALLATION_ID = System.getProperty("sdk.installationId");

        if (USER == null || PASS == null || MERCHANT_CODE == null || INSTALLATION_ID == null)
        {
            throw new IllegalStateException("Tests ran without credentials specified");
        }

        GATEWAY_CONTEXT = new GatewayContext(Environment.SANDBOX, new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID));

        // Redirect jul to logback
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        Logger.getLogger("").info("Starting test...");
    }

    protected LastEvent pollUntil(OrderDetails orderDetails, LastEvent expectedLastEvent) throws Exception
    {
        boolean ready = false;
        int attempts = 0;
        LastEvent result = null;

        do
        {
            try
            {
                Payment payment = new OrderInquiryRequest(orderDetails).send(GATEWAY_CONTEXT);
                result = payment.getLastEvent();

                if (expectedLastEvent.equals(result))
                {
                    ready = true;
                }
            }
            catch (WpgException e)
            {
            }

            // Sleep until ready, probably replication delay...
            if (!ready)
            {
                Thread.sleep(ORDER_INQUIRY_DELAY);
            }
        }
        while (!ready && attempts++ < ORDER_INQUIRY_ATTEMPTS);

        if (result == null)
        {
            throw new IllegalStateException("Order not ready - unable to inquire status");
        }
        else if (!result.equals(expectedLastEvent))
        {
            throw new IllegalStateException("Order does not have expected last event - currently: " + result + ", expected: " + expectedLastEvent);
        }

        return result;
    }

    protected void assertStatusCode(String url, int expectedStatusCode) throws IOException
    {
        LOG.fine("asserted url: " + url);

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setInstanceFollowRedirects(true);

        int statusCode = conn.getResponseCode();
        assertThat(statusCode, is(expectedStatusCode));
    }

    protected OrderDetails createGenericOrder() throws WpgException
    {
        OrderDetails orderDetails = new OrderDetails("threeds test order", new Amount(Currency.GBP, 2L, 1000L));

        CardDetails cardDetails = new CardDetails("4444333322221129", 1L, 2030L, "test");
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("accepts", "user agent"));
        CardPaymentRequest request = new CardPaymentRequest(orderDetails, cardDetails, shopper);
        PaymentResponse response = request.send(GATEWAY_CONTEXT);
        assertEquals(PaymentStatus.PAYMENT_RESULT, response.getStatus());

        return orderDetails;
    }

}
