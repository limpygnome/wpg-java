package com.worldpay.sdk.wpg.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.payment.result.CvcResult;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class CvcResultSerializer
{
    public static CvcResult read(XmlBuilder builder)
    {
        CvcResult result = null;
        if (builder.hasE("CVCResultCode"))
        {
            String resultCode = builder.a("description");
            result = new CvcResult(resultCode);
            builder.up();
        }
        return result;
    }
}
