package com.worldpay.sdk.wpg.internal.xml.serializer.modification;

import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.serializer.AmountSerializer;
import com.worldpay.sdk.wpg.request.modification.batchable.CancelOrRefundRequest;
import com.worldpay.sdk.wpg.request.modification.batchable.CaptureOrderRequest;
import com.worldpay.sdk.wpg.request.modification.batchable.RefundOrderRequest;
import com.worldpay.sdk.wpg.request.modification.nonbatchable.AddBackOfficeCodeRequest;
import com.worldpay.sdk.wpg.request.modification.nonbatchable.AuthoriseOrderRequest;
import com.worldpay.sdk.wpg.request.modification.nonbatchable.CancelOrderRequest;
import com.worldpay.sdk.wpg.request.modification.nonbatchable.DisputeDefenceRequest;
import com.worldpay.sdk.wpg.request.modification.nonbatchable.IncreaseAuthorisationRequest;

public class OrderModificationSerializer
{

    public static void decorateForRequest(XmlBuildParams params, String orderCode)
    {
        XmlBuilder builder = params.xmlBuilder();

        builder.e("modify")
                .e("orderModification")
                    .a("orderCode", orderCode);
    }

    public static void decorate(XmlBuildParams params, AddBackOfficeCodeRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();
        builder.e("addBackOfficeCode").a("backOfficeCode", request.getBackOfficeCode());
    }

    public static void decorate(XmlBuildParams params, AuthoriseOrderRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();
        builder.e("authorise").a("authorisationCode", request.getAuthorisationCode());
    }

    public static void decorate(XmlBuildParams params, CancelOrderRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();
        builder.e("cancel");
    }

    public static void decorate(XmlBuildParams params, CancelOrRefundRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();
        builder.e("cancelOrRefund");
    }

    public static void decorate(XmlBuildParams params, CaptureOrderRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();
        builder.e("capture");
        AmountSerializer.write(builder, request.getAmount());
    }

    public static void decorate(XmlBuildParams params, DisputeDefenceRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();

        String text = request.getText();
        String attachment = request.getAttachment();

        builder.e("defend");

        if (text != null)
        {
            builder.e("disputeDefence").cdata(text);
        }

        if (attachment != null)
        {
            builder.e("disputeAttachment").cdata(attachment);
        }
    }

    public static void decorate(XmlBuildParams params, IncreaseAuthorisationRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();
        builder.e("increaseAuthorisation");
        AmountSerializer.write(builder, request.getAmount());
    }

    public static void decorate(XmlBuildParams params, RefundOrderRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();
        builder.e("refund");
        AmountSerializer.write(builder, request.getAmount());
    }

}
