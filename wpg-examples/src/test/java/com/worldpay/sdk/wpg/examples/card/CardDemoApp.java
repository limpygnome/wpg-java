package com.worldpay.sdk.wpg.examples.card;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsRequired;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;

public class CardDemoApp
{

    public static void main(String[] args)
    {
        // setup gateway details
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, new UserPassAuth("NGPPTESTMERCH1", "live2014", "NGPPTESTMERCH1", "1008775"));

        // build order details
        OrderDetails orderDetails = new OrderDetails("test order", new Amount(Currency.GBP, 2L, 1234L));
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36"));

        // build card details
        CardDetails cardDetails = new CardDetails("4444333322221111", 1, 2020, "Cardholder name", 123);

        try
        {
            // create order
            PaymentResponse paymentResponse = new CardPaymentRequest()
                    .orderDetails(orderDetails)
                    .cardDetails(cardDetails)
                    .shopper(shopper)
                    .send(gatewayContext);

            switch (paymentResponse.getStatus())
            {
                case THREEDS_REQUESTED:
                    ThreeDsRequired threeDs = paymentResponse.getThreeDsRequired();
                    System.out.println("3ds required - issuer URL:" + threeDs.getIssuerURL() + ", paRes: " + threeDs.getPaRequest());
                    break;
                case PAYMENT_RESULT:
                    Payment payment = paymentResponse.getPayment();
                    System.out.println("payment - lastEvent: " + payment.getLastEvent());
                    break;
                default:
                    throw new IllegalStateException("Unhandled response - status=" + paymentResponse.getStatus());
            }
        }
        catch (WpgException e)
        {
            e.printStackTrace();
        }
    }

}
