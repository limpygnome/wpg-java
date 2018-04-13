package com.worldpay.sdk.wpg.examples.threeds;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.threeds.SubmitThreeDSRequest;

// TODO setup card payment with 3ds enabled, open browser with simulator, have local socket for response
public class SubmitThreeDSDemoApp
{

    public static void main(String[] args)
    {
        Auth auth = new UserPassAuth("NGPPTESTMERCH1", "live2014", "NGPPTESTMERCH1", "1008775");
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, auth);

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
