package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class SessionSerializer
{

    public static void decorateOrderPaymentDetails(XmlBuildParams params, Shopper shopper)
    {
        String sessionId = params.sessionContext().getSessionId();
        String shopperIpAddress = (shopper != null ? shopper.getIpAddress() : null);

        XmlBuilder builder = params.xmlBuilder();

        builder.e("paymentDetails");

        // build session element
        if (sessionId != null || shopperIpAddress != null)
        {
            builder.e("session");

            if (shopperIpAddress != null && shopperIpAddress.length() > 0)
            {
                builder.a("shopperIPAddress", shopperIpAddress);
            }
            if (sessionId != null & sessionId.length() > 0)
            {
                builder.a("id", sessionId);
            }
            builder.up();
        }

        builder.up();
    }

}
