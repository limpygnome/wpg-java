package com.worldpay.sdk.wpg.integration.modification.batchable;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.modification.batchable.CaptureOrderRequest;
import org.junit.Before;
import org.junit.Test;

public class CaptureOrderRequestTest extends BaseIntegrationTest
{
    private OrderDetails orderDetails;

    @Before
    public void setupOrder() throws WpgException
    {
        orderDetails = createGenericOrder();
    }

    @Test
    public void capture() throws Exception
    {
        // When
        new CaptureOrderRequest(orderDetails.getOrderCode(), "reference", orderDetails.getAmount())
                .send(GATEWAY_CONTEXT);

        // TODO unable to verify any further at this time.
    }

}
