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
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.request.apm.PayPalPaymentRequest;

public class PayPalAdvancedDemoApp
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
        Shopper shopper = new Shopper("test@test.com");

        try
        {
            // create order
            RedirectUrl response = new PayPalPaymentRequest()
                    .orderDetails(orderDetails)
                    .billingAddress(address)
                    .shippingAddress(address)
                    .shopper(shopper)
                    .successURL("https://success")
                    .failureURL("https://failure")
                    .cancelURL("https://cancel")
                    .languageCode("fr")
                    .send(gatewayContext);

            System.out.println(response.getUrl());
        }
        catch (WpgConnectionException e)
        {
            e.printStackTrace();
        }
        catch (WpgRequestException e)
        {
            e.printStackTrace();
        }
        catch (WpgMalformedXmlException e)
        {
            e.printStackTrace();
        }
        catch (WpgErrorResponseException e)
        {
            e.printStackTrace();
        }
    }

}
