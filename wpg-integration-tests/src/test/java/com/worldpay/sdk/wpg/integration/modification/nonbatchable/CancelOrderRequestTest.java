package com.worldpay.sdk.wpg.integration.modification.nonbatchable;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.modification.nonbatchable.CancelOrderRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CancelOrderRequestTest extends BaseIntegrationTest
{
    private OrderDetails orderDetails;

    @Before
    public void setupOrder() throws WpgException
    {
        orderDetails = createGenericOrder();
    }

    @Test
    public void cancel_asExpected() throws Exception
    {
        new CancelOrderRequest(orderDetails.getOrderCode())
            .send(GATEWAY_CONTEXT);
    }

}
