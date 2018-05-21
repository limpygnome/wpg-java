package com.worldpay.sdk.wpg.examples.hpp;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.Country;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.hosted.HostedPaymentPagesRequest;

import static com.worldpay.sdk.wpg.examples.AuthConstants.INSTALLATION_ID;
import static com.worldpay.sdk.wpg.examples.AuthConstants.MERCHANT_CODE;
import static com.worldpay.sdk.wpg.examples.AuthConstants.PASS;
import static com.worldpay.sdk.wpg.examples.AuthConstants.USER;

public class HppDemoApp
{

    public static void main(String[] args)
    {
        // setup gateway details
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID));

        // build order details
        Amount amount = new Amount(Currency.GBP, 2L, 1000L);
        OrderDetails orderDetails = new OrderDetails("test order", amount);

        try
        {
            // create order
            RedirectUrl response = new HostedPaymentPagesRequest()
                    .orderDetails(orderDetails)
                    .send(gatewayContext);

            System.out.println(response.getUrl());
        }
        catch (WpgException e)
        {
            e.printStackTrace();
        }
    }

}
