package com.worldpay.sdk.wpg.integration.modification.batchable;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.modification.batchable.CancelOrRefundRequest;
import org.junit.Before;
import org.junit.Test;

public class CancelOrRefundRequestTest extends BaseIntegrationTest
{
    private OrderDetails orderDetails;

    @Before
    public void setupOrder() throws WpgException
    {
        orderDetails = createGenericOrder();
    }

    @Test
    public void cancel() throws Exception
    {
        // Given
        pollUntil(orderDetails, LastEvent.AUTHORISED);

        // When
        new CancelOrRefundRequest(orderDetails.getOrderCode())
                .send(GATEWAY_CONTEXT);
    }

}
