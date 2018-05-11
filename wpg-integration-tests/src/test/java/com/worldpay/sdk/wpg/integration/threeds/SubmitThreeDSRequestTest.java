package com.worldpay.sdk.wpg.integration.threeds;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.request.threeds.SubmitThreeDSRequest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SubmitThreeDSRequestTest extends BaseIntegrationTest
{
    private SessionContext sessionContext;
    private OrderDetails orderDetails;

    @Before
    public void setupCardPayment() throws Exception
    {
        sessionContext = new SessionContext();
        orderDetails = new OrderDetails("threeds test order", new Amount(Currency.GBP, 2L, 1000L));

        // Send initial card payment
        CardDetails cardDetails = new CardDetails("4444333322221129", 1L, 2030L, "3DS");
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("accepts", "user agent"));
        CardPaymentRequest initialRequest = new CardPaymentRequest(orderDetails, cardDetails, shopper);
        PaymentResponse initialPaymentResponse = initialRequest.send(GATEWAY_CONTEXT, sessionContext);

        // Check threeds is required
        assertNotNull(initialPaymentResponse.getThreeDsDetails());
    }

    @Test
    public void sendRubbish() throws Exception
    {
        // Send rubbish as threeds
        SubmitThreeDSRequest request = new SubmitThreeDSRequest(orderDetails, "rubbish pa response");
        PaymentResponse paymentResponse = request.send(GATEWAY_CONTEXT, sessionContext);
        // TODO finish
    }

    @Test
    public void sendLegit() throws Exception
    {
        // TODO see if simulator pares is random
    }

}
