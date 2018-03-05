package com.worldpay.sdk.wpg.xml.serializer;

import com.worldpay.sdk.wpg.domain.Session;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class SessionSerializer
{

    public static void decorate(XmlBuildParams params, Session session)
    {
        String sessionId = params.sessionContext().getSessionId();

        if (session != null)
        {
            XmlBuilder builder = params.xmlBuilder();

            builder.e("submit").e("order").e("paymentDetails");

            // build session element
            builder.e("session")
                    .a("shopperIPAddress", session.getShopperIpAddress())
                    .a("id", sessionId);

            builder.reset();
        }
    }

}
