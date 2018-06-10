package com.worldpay.sdk.wpg.examples.tokenisation;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.shopper.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenisationPaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.tokenisation.TokenPaymentRequest;

import static com.worldpay.sdk.wpg.examples.AuthConstants.INSTALLATION_ID;
import static com.worldpay.sdk.wpg.examples.AuthConstants.MERCHANT_CODE;
import static com.worldpay.sdk.wpg.examples.AuthConstants.PASS;
import static com.worldpay.sdk.wpg.examples.AuthConstants.USER;

public class CaptureCvcDemoApp
{

    public static void main(String[] args)
    {
        // setup gateway details
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID));

        try
        {
            ShopperBrowser browser = new ShopperBrowser("accepts", "user agent");
            Shopper shopper = new Shopper("test@test.com", "123.123.123.123", browser, "shopper id");
            OrderDetails orderDetails = new OrderDetails("test", new Amount(Currency.EUR, 2L, 1234L));

            // When
            TokenisationPaymentResponse response = new TokenPaymentRequest()
                    .paymentTokenId("1234567890356789")
                    .scope(TokenScope.SHOPPER)
                    .orderDetails(orderDetails)
                    .shopper(shopper)
                    .captureCvc(true)
                    .send(gatewayContext);

            System.out.println(response.getCaptureCvcUrl().getUrl());
        }
        catch (WpgException e)
        {
            e.printStackTrace();
        }
    }

}
