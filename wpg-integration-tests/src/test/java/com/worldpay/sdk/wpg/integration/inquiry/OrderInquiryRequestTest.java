package com.worldpay.sdk.wpg.integration.inquiry;

import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.request.inquiry.OrderInquiryRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderInquiryRequestTest extends BaseIntegrationTest
{
    private OrderDetails orderDetails;

    @Before
    public void setupOrder() throws Exception
    {
        orderDetails = new OrderDetails("threeds test order", new Amount(Currency.GBP, 2L, 1000L));

        CardDetails cardDetails = new CardDetails("4444333322221129", 1L, 2030L, "test");
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("accepts", "user agent"));
        CardPaymentRequest request = new CardPaymentRequest(orderDetails, cardDetails, shopper);
        PaymentResponse response = request.send(GATEWAY_CONTEXT);
        assertEquals(PaymentStatus.PAYMENT_RESULT, response.getStatus());
    }

    @Test
    public void inquiry_asExpected() throws Exception
    {
        // Due to replication delay, poll until auth
        pollUntil(orderDetails, LastEvent.AUTHORISED);

        // Check received as expected, but not in detail as tested elsewhere based on PM
        Payment payment = new OrderInquiryRequest(orderDetails).send(GATEWAY_CONTEXT);
        assertNotNull("Should have returned payment (replication delay/)", payment);
        assertEquals("Should have AUTHORISED as last event", LastEvent.AUTHORISED, payment.getLastEvent());
        assertNotNull("Should have card details", payment.getCardDetails());
    }

}
