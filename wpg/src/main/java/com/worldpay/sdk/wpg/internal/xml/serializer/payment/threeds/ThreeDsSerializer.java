package com.worldpay.sdk.wpg.internal.xml.serializer.payment.threeds;

import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsDetails;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class ThreeDsSerializer
{

    public static ThreeDsDetails read(XmlBuilder builder)
    {
        String issuerURL = builder.getCdata("issuerURL");
        String paRequest = builder.getCdata("paRequest");

        ThreeDsDetails threeDsDetails = new ThreeDsDetails(issuerURL, paRequest);
        return threeDsDetails;
    }

}
