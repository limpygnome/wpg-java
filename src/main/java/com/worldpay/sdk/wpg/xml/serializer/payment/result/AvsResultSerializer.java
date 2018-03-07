package com.worldpay.sdk.wpg.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.payment.result.AvsResult;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class AvsResultSerializer
{

    public static AvsResult read(XmlBuilder builder)
    {
        AvsResult result = null;
        if (builder.hasE("AVSResultCode"))
        {
            String resultCode = builder.a("description");
            result = new AvsResult(resultCode);
            builder.up();
        }
        return result;
    }

}
