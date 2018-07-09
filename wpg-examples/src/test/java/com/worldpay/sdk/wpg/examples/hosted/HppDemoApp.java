package com.worldpay.sdk.wpg.examples.hosted;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.hosted.HostedPaymentPagesRequest;

import static com.worldpay.sdk.wpg.examples.AuthConstants.INSTALLATION_ID;
import static com.worldpay.sdk.wpg.examples.AuthConstants.MERCHANT_CODE;
import static com.worldpay.sdk.wpg.examples.AuthConstants.PASS;
import static com.worldpay.sdk.wpg.examples.AuthConstants.USER;

public class HppDemoApp
{

    public static void main(String[] args) throws WpgException
    {
        // setup gateway details
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID));

        // build order details
        Amount amount = new Amount("GBP", 2L, 1000L);
        OrderDetails orderDetails = new OrderDetails("test order", amount);

        // create order
        RedirectUrl response = new HostedPaymentPagesRequest()
                .orderDetails(orderDetails)
                .send(gatewayContext);

        System.out.println(response.getUrl());
    }

}
