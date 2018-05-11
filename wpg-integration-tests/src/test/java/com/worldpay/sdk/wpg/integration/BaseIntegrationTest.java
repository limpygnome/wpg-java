package com.worldpay.sdk.wpg.integration;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.inquiry.OrderInquiryRequest;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.logging.Logger;

public class BaseIntegrationTest
{
    protected static final String USER;
    protected static final String PASS;
    protected static final String MERCHANT_CODE;
    protected static final String INSTALLATION_ID;
    protected static final GatewayContext GATEWAY_CONTEXT;

    static
    {
        USER = System.getProperty("sdk.user");
        PASS = System.getProperty("sdk.pass");
        MERCHANT_CODE = System.getProperty("sdk.merchantCode");
        INSTALLATION_ID = System.getProperty("sdk.installationId");

        if (USER == null || PASS == null || MERCHANT_CODE == null || INSTALLATION_ID == null)
        {
            throw new IllegalStateException("Tests ran without credentials specified");
        }

        GATEWAY_CONTEXT = new GatewayContext(Environment.SANDBOX, new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID));

        // Redirect jul to logback
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        Logger.getLogger("").info("Starting test...");
    }

    protected LastEvent pollUntil(OrderDetails orderDetails, LastEvent expectedLastEvent, int maxAttempts) throws Exception
    {
        boolean ready = false;
        int attempts = 0;
        LastEvent result = null;

        do
        {
            try
            {
                Payment payment = new OrderInquiryRequest(orderDetails).send(GATEWAY_CONTEXT);
                result = payment.getLastEvent();

                if (expectedLastEvent.equals(result))
                {
                    ready = true;
                }
            }
            catch (WpgException e)
            {
            }

            // Sleep until ready, probably replication delay...
            if (!ready)
            {
                Thread.sleep(1000);
            }
        }
        while (!ready && attempts < maxAttempts);

        if (result == null)
        {
            throw new IllegalStateException("Order not ready - unable to inquire status");
        }

        return result;
    }

}
