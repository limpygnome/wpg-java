package com.worldpay.sdk.wpg.examples.apm;

import static com.worldpay.sdk.wpg.examples.AuthConstants.INSTALLATION_ID;
import static com.worldpay.sdk.wpg.examples.AuthConstants.MERCHANT_CODE;
import static com.worldpay.sdk.wpg.examples.AuthConstants.PASS;
import static com.worldpay.sdk.wpg.examples.AuthConstants.USER;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.apm.PayPalLanguage;
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
        Auth auth = new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID);
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, auth);

        // build order details
        Amount amount = new Amount(Currency.GBP, 2L, 1000L);
        OrderDetails orderDetails = new OrderDetails("test order", amount);

        Address address = new Address("123 test address", "blah", "1234", "GB");
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
                    .language(PayPalLanguage.FRENCH)
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
