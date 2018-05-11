package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.request.inquiry.OrderInquiryRequest;

public class OrderInquirySerializer
{

    public static void decorate(XmlBuildParams params, OrderInquiryRequest request)
    {
        String orderCode = request.getOrderCode();

        XmlBuilder builder = params.xmlBuilder();
        builder.e("inquiry")
                .e("orderInquiry").a("orderCode", orderCode);
    }

}
