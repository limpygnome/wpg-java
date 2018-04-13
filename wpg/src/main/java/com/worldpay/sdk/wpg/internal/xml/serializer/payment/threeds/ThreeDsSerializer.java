package com.worldpay.sdk.wpg.internal.xml.serializer.payment.threeds;

import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsRequired;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class ThreeDsSerializer
{

    public static ThreeDsRequired read(XmlBuilder builder)
    {
        String issuerURL = builder.getCdata("issuerURL");
        String paRequest = builder.getCdata("paRequest");

        ThreeDsRequired threeDsRequired = new ThreeDsRequired(issuerURL, paRequest);
        return threeDsRequired;
    }

}
