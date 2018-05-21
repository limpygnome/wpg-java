package com.worldpay.sdk.wpg.integration.modification.batchable;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.modification.batchable.RefundOrderRequest;
import org.junit.Before;
import org.junit.Test;

public class RefundOrderRequestTest extends BaseIntegrationTest
{
    private OrderDetails orderDetails;

    @Before
    public void setupOrder() throws WpgException
    {
        orderDetails = createGenericOrder();
    }

    @Test
    public void refund() throws Exception
    {
        // When
        new RefundOrderRequest(orderDetails.getOrderCode(), "reference", orderDetails.getAmount())
                .send(GATEWAY_CONTEXT);

        // Then
        // TODO unable to verify any further at this time.
    }

}
