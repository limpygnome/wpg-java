package com.worldpay.sdk.wpg;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CountryCode;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.request.hosted.HostedPaymentPagesRequest;
import com.worldpay.sdk.wpg.response.redirect.RedirectUrlResponse;

public class HppDemoApp
{

    public static void main(String[] args)
    {
        // setup sharable gateway
        Auth auth = new UserPassAuth("NGPPTESTMERCH1", "live2014", "NGPPTESTMERCH1", 100875L);
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, auth);

        // build payment details
        Amount amount = new Amount(Currency.GBP, 2L, 1000L);
        OrderDetails orderDetails = new OrderDetails(amount);

        Address billingAddress = new Address("123 test address", "1234", CountryCode.GREAT_BRITAIN);
        Address shippingAddress = new Address("123 test address", "1234", CountryCode.GREAT_BRITAIN);
        Shopper shopper = new Shopper("test@test.com");

        // send payment(s)
        try
        {
            RedirectUrlResponse response = (RedirectUrlResponse) new HostedPaymentPagesRequest()
                    .orderDetails(orderDetails)
                    .billingAddress(billingAddress)
                    .shippingAddress(shippingAddress)
                    .shopper(shopper)
                    .send(gatewayContext, new SessionContext());

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
    }

}
