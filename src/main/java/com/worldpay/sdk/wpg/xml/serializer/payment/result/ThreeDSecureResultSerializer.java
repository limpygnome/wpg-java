package com.worldpay.sdk.wpg.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.payment.result.ThreeDSecureResult;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class ThreeDSecureResultSerializer
{

    public static ThreeDSecureResult read(XmlBuilder builder)
    {
        ThreeDSecureResult result = null;

        if (builder.hasE("ThreeDSecureResult"))
        {
            String description = builder.a("description");
            String eci = null;
            String cavv = null;

            if (builder.hasE("eci"))
            {
                eci = builder.cdata();
                builder.up();
            }

            if (builder.hasE("cavv"))
            {
                cavv = builder.cdata();
                builder.up();
            }

            result = new ThreeDSecureResult(description, eci, cavv);
            builder.up();
        }

        return result;
    }

}
