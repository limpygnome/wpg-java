package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class SessionSerializer
{

    public static void decorate(XmlBuildParams params, Shopper shopper)
    {
        String sessionId = params.sessionContext().getSessionId();
        String shopperIpAddress = (shopper != null ? shopper.getIpAddress() : "");

        XmlBuilder builder = params.xmlBuilder();

        builder.e("submit").e("order").e("paymentDetails");

        // build session element
        builder.e("session")
                .a("shopperIPAddress", shopperIpAddress)
                .a("id", sessionId);

        builder.reset();
    }

}
