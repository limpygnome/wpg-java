package com.worldpay.sdk.wpg.internal.xml.serializer.modification;

import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.serializer.AmountSerializer;
import com.worldpay.sdk.wpg.request.modification.BatchModificationCancelRequest;
import com.worldpay.sdk.wpg.request.modification.BatchModificationInquiryRequest;
import com.worldpay.sdk.wpg.request.modification.BatchModificationRequest;
import com.worldpay.sdk.wpg.request.modification.batchable.BatchModificationItem;
import com.worldpay.sdk.wpg.request.modification.batchable.CancelOrRefundRequest;
import com.worldpay.sdk.wpg.request.modification.batchable.CaptureOrderRequest;
import com.worldpay.sdk.wpg.request.modification.batchable.ReferralOrderRequest;
import com.worldpay.sdk.wpg.request.modification.batchable.RefundOrderRequest;

public class BatchOrderModificationSerializer
{

    public static void decorate(XmlBuildParams params, BatchModificationRequest request)
    {
        UserPassAuth auth = (UserPassAuth) params.gatewayContext().getAuth();

        // Create a new builder for batch service schema
        XmlBuilder builder = XmlBuilder.createBatchService();
        params.setBuilder(builder);

        // Add top-level attribs
        builder.a("merchantCode", auth.getMerchantCode());
        builder.a("batchCode", request.getBatchCode());

        // Add items
        for (BatchModificationItem item : request.getItems())
        {
            decorate(builder, item);
        }
    }

    public static void decorateForInquiry(XmlBuildParams params, BatchModificationInquiryRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();

        builder.e("inquiry").e("batchInquiry")
                .a("merchantBatchCode", request.getBatchCode());
    }

    public static void decorateForCancel(XmlBuildParams params, BatchModificationCancelRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();

        builder.e("modify").e("batchModification")
                .a("merchantBatchCode", request.getBatchCode())
                .e("cancel");
    }

    private static void decorate(XmlBuilder builder, BatchModificationItem item)
    {
        if (item instanceof CancelOrRefundRequest)
        {
            CancelOrRefundRequest request = (CancelOrRefundRequest) item;
            decorate(builder, request);
        }
        else if (item instanceof CaptureOrderRequest)
        {
            CaptureOrderRequest request = (CaptureOrderRequest) item;
            decorate(builder, request);
        }
        else if (item instanceof ReferralOrderRequest)
        {
            ReferralOrderRequest request = (ReferralOrderRequest) item;
            decorate(builder, request);
        }
        else if (item instanceof RefundOrderRequest)
        {
            RefundOrderRequest request = (RefundOrderRequest) item;
            decorate(builder, request);
        }
        else
        {
            throw new IllegalArgumentException("Class " + (item != null ? item.getClass() : "null") + " not supported for batch order modifications");
        }
    }

    private static void decorate(XmlBuilder builder, CancelOrRefundRequest request)
    {
        builder.e("cancelOrRefund").a("orderCode", request.getOrderCode()).up();
    }

    private static void decorate(XmlBuilder builder, CaptureOrderRequest request)
    {
        builder.e("capture").a("reference", request.getReference()).a("orderCode", request.getOrderCode());
        AmountSerializer.write(builder, request.getAmount());
        builder.up();
    }

    private static void decorate(XmlBuilder builder, ReferralOrderRequest request)
    {
        String action;

        switch (request.getAction())
        {
            case BLACKLIST:
                action = "stop";
                break;
            case WHITELIST:
                action = "white";
                break;
            case REMOVE:
                action = "remove";
                break;
            default:
                throw new IllegalStateException("Unhandled referral action: " + request.getAction());
        }

        builder.e("referral").a("reason", request.getReason()).a("action", action)
                .e("cardReferral").cdata(request.getCardReferral()).up()
                .up();
    }

    private static void decorate(XmlBuilder builder, RefundOrderRequest request)
    {
        builder.e("refund").a("reference", request.getReference()).a("orderCode", request.getOrderCode());
        AmountSerializer.write(builder, request.getAmount());
        builder.up();
    }



}
