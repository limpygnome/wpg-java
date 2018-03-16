package com.worldpay.sdk.wpg.internal.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.payment.result.ISO8583Result;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class ISO8583ResultSerializer
{

    public static ISO8583Result read(XmlBuilder builder)
    {
        ISO8583Result result = null;

        if (builder.hasE("ISO8583ReturnCode"))
        {
            String code = builder.a("code");
            String description = builder.a("description");
            result = new ISO8583Result(code, description);
            builder.up();
        }

        return result;
    }

}
