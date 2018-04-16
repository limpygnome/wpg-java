package com.worldpay.sdk.wpg.examples.threeds;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.threeds.SubmitThreeDSRequest;

import static com.worldpay.sdk.wpg.examples.AuthConstants.INSTALLATION_ID;
import static com.worldpay.sdk.wpg.examples.AuthConstants.MERCHANT_CODE;
import static com.worldpay.sdk.wpg.examples.AuthConstants.PASS;
import static com.worldpay.sdk.wpg.examples.AuthConstants.USER;

// TODO setup card payment with 3ds enabled, open browser with simulator, have local socket for response
public class SubmitThreeDSDemoApp
{

    public static void main(String[] args)
    {
        // setup gateway details
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID));

        // submit threeds response from issuer
        try
        {
            PaymentResponse response = new SubmitThreeDSRequest()
                    .orderCode("example-order-code-12345")
                    .paResponse("xyz")
                    .send(gatewayContext);

            System.out.println(response.getPayment().getLastEvent());
        }
        catch (WpgException e)
        {
            e.printStackTrace();
        }
    }

}
