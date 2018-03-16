package com.worldpay.sdk.wpg.examples.apm;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CountryCode;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.apm.PayPalPaymentRequest;

public class PayPalTokenisationDemoApp
{

    public static void main(String[] args)
    {
        // setup gateway details
        Auth auth = new UserPassAuth("NGPPTESTMERCH1", "live2014", "NGPPTESTMERCH1", "1008775");
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, auth);

        // build order details
        Amount amount = new Amount(Currency.GBP, 2L, 1000L);
        OrderDetails orderDetails = new OrderDetails("test order", amount);
        Address address = new Address("123 test address", "blah", "1234", CountryCode.GREAT_BRITAIN);

        // provide a (unique) shopper identifier, and details for token
        Shopper shopper = new Shopper("test@test.com", "shopper123");
        CreateTokenDetails tokenDetails = new CreateTokenDetails("TOKEN_EVENT_1234", "monthly subscription");

        try
        {
            // create order
            RedirectUrl response = new PayPalPaymentRequest()
                    .orderDetails(orderDetails)
                    .billingAddress(address)
                    .shippingAddress(address)
                    .shopper(shopper)
                    .resultURL("https://continue")
                    .tokeniseForReoccurringPayments(tokenDetails)
                    .send(gatewayContext);

            System.out.println(response.getUrl());
        }
        catch (WpgException e)
        {
            e.printStackTrace();
        }
    }

}
