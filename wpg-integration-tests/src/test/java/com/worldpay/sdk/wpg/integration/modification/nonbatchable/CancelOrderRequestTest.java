package com.worldpay.sdk.wpg.integration.modification.nonbatchable;

import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.request.modification.nonbatchable.CancelOrderRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CancelOrderRequestTest extends BaseIntegrationTest
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
    public void cancel_asExpected() throws Exception
    {
        // Do an inquiry to check order is available; wait until it's AUTHORISED...
        LastEvent lastEvent = pollUntil(orderDetails, LastEvent.AUTHORISED, 10);
        assertEquals("Should be in AUTHORISED state", LastEvent.AUTHORISED, lastEvent);

        // Send the request
        CancelOrderRequest request = new CancelOrderRequest(orderDetails.getOrderCode());
        request.send(GATEWAY_CONTEXT);
    }

}
