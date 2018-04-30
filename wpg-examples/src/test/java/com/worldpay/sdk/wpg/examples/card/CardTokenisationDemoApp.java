package com.worldpay.sdk.wpg.examples.card;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.CountryCode;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;

import static com.worldpay.sdk.wpg.examples.AuthConstants.INSTALLATION_ID;
import static com.worldpay.sdk.wpg.examples.AuthConstants.MERCHANT_CODE;
import static com.worldpay.sdk.wpg.examples.AuthConstants.PASS;
import static com.worldpay.sdk.wpg.examples.AuthConstants.USER;

public class CardTokenisationDemoApp
{

    public static void main(String[] args)
    {
        // setup gateway details
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID));

        // build order details
        Amount amount = new Amount(Currency.GBP, 2L, 1234L);
        OrderDetails orderDetails = new OrderDetails("test order", amount);
        Address address = new Address("123 test address", "blah", "1234", CountryCode.GREAT_BRITAIN);

        // provide a (unique) shopper identifier, and details for token
        ShopperBrowser browser = new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36");
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", browser, "shopper123");
        CreateTokenDetails tokenDetails = new CreateTokenDetails("TOKEN_EVENT_1234", "monthly subscription");

        // build card details
        CardDetails cardDetails = new CardDetails("4444333322221111", 1, 2020, "Cardholder name", "123", address);

        try
        {
            // create order
            PaymentResponse paymentResponse = new CardPaymentRequest()
                    .orderDetails(orderDetails)
                    .cardDetails(cardDetails)
                    .billingAddress(address)
                    .shippingAddress(address)
                    .shopper(shopper)
                    .tokeniseForReoccurringPayments(tokenDetails)
                    .send(gatewayContext);

            switch (paymentResponse.getStatus())
            {
                case THREEDS_REQUESTED:
                    ThreeDsDetails threeDs = paymentResponse.getThreeDsDetails();
                    System.out.println("3ds required - issuer URL:" + threeDs.getIssuerURL() + ", paRes: " + threeDs.getPaRequest());
                    break;
                case PAYMENT_RESULT:
                    Payment payment = paymentResponse.getPayment();
                    System.out.println("payment - lastEvent: " + payment.getLastEvent());
                    break;
                default:
                    throw new IllegalStateException("Unhandled response - type=" + paymentResponse.getStatus());
            }
        }
        catch (WpgException e)
        {
            e.printStackTrace();
        }
    }

}
