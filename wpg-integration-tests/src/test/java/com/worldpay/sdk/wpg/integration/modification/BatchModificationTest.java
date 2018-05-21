package com.worldpay.sdk.wpg.integration.modification;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.notification.BatchInquiry;
import com.worldpay.sdk.wpg.domain.notification.BatchStatus;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.modification.BatchModificationCancelRequest;
import com.worldpay.sdk.wpg.request.modification.BatchModificationInquiryRequest;
import com.worldpay.sdk.wpg.request.modification.BatchModificationRequest;
import com.worldpay.sdk.wpg.request.modification.batchable.CancelOrRefundRequest;
import com.worldpay.sdk.wpg.request.modification.batchable.CaptureOrderRequest;
import com.worldpay.sdk.wpg.request.modification.batchable.RefundOrderRequest;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Not currently possible to test errors, as batches are not processed immediately (or some time after).
 */
public class BatchModificationTest extends BaseIntegrationTest
{

    @Test
    public void inquiry_asExpected() throws Exception
    {
        // Given
        // -- Setup some orders
        OrderDetails order1 = createGenericOrder();
        OrderDetails order2 = createGenericOrder();
        OrderDetails order3 = createGenericOrder();

        // -- Setup modifications
        CancelOrRefundRequest cancel = new CancelOrRefundRequest(order1);
        CaptureOrderRequest capture = new CaptureOrderRequest(order2, "reference", order2.getAmount());
        RefundOrderRequest refund = new RefundOrderRequest(order3, "reference", order3.getAmount());

        // -- Setup batch of modifications
        BatchModificationRequest batchRequest = new BatchModificationRequest()
                .add(cancel, capture, refund);

        batchRequest.send(GATEWAY_CONTEXT);

        // When
        BatchInquiry batchInquiry = new BatchModificationInquiryRequest(batchRequest.getBatchCode())
                .send(GATEWAY_CONTEXT);

        // Then
        assertNotNull(batchInquiry);
        assertThat(batchInquiry.getStatus(), is(BatchStatus.ORDERS_SAVED));
        assertThat(batchInquiry.getTransactions(), is(3));
        assertTrue(batchInquiry.getErrors().isEmpty());
    }

    @Test
    public void cancel_asExpected() throws WpgException
    {
        // Given
        // -- Setup some orders
        OrderDetails order1 = createGenericOrder();
        OrderDetails order2 = createGenericOrder();
        OrderDetails order3 = createGenericOrder();

        // -- Setup modifications
        CancelOrRefundRequest cancel = new CancelOrRefundRequest(order1);
        CaptureOrderRequest capture = new CaptureOrderRequest(order2, "reference", order2.getAmount());
        RefundOrderRequest refund = new RefundOrderRequest(order3, "reference", order3.getAmount());

        // -- Setup batch of modifications
        BatchModificationRequest batchRequest = new BatchModificationRequest()
                .add(cancel, capture, refund);

        batchRequest.send(GATEWAY_CONTEXT);

        // When
        new BatchModificationCancelRequest(batchRequest.getBatchCode()).send(GATEWAY_CONTEXT);

        // Then
        BatchInquiry batchInquiry = new BatchModificationInquiryRequest(batchRequest.getBatchCode())
                .send(GATEWAY_CONTEXT);

        assertNotNull(batchInquiry);
        assertThat(batchInquiry.getStatus(), is(BatchStatus.CANCELLED));
        assertThat(batchInquiry.getTransactions(), is(3));
        assertTrue(batchInquiry.getErrors().isEmpty());
    }

}
