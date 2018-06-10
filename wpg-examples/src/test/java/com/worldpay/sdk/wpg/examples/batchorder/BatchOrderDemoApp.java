package com.worldpay.sdk.wpg.examples.batchorder;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.card.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.batch.BatchOrderRequest;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;

import static com.worldpay.sdk.wpg.examples.AuthConstants.INSTALLATION_ID;
import static com.worldpay.sdk.wpg.examples.AuthConstants.MERCHANT_CODE;
import static com.worldpay.sdk.wpg.examples.AuthConstants.PASS;
import static com.worldpay.sdk.wpg.examples.AuthConstants.USER;

public class BatchOrderDemoApp
{
    private static final int TOTAL_ORDERS = 1;

    public static void main(String[] args)
    {
        // setup gateway details
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID));

        // setup request for batch orders
        BatchOrderRequest request = new BatchOrderRequest();

        // add lots of orders
        populateWithOrders(request);

        // send it
        try
        {
            request.send(gatewayContext);
        }
        catch (WpgException e)
        {
            e.printStackTrace();;
        }
    }

    private static void populateWithOrders(BatchOrderRequest request)
    {
        for (int i = 0; i < TOTAL_ORDERS; i++)
        {
            CardPaymentRequest order = new CardPaymentRequest()
                    .orderDetails(new OrderDetails("test order", new Amount(Currency.GBP, 2L, 1234L)))
                    .cardDetails(new CardDetails("4444333322221111", 1L, 2020L, "Cardholder name", "123"));

            request.add(order);
        }
    }

}
