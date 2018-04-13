package com.worldpay.sdk.wpg.internal.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.payment.result.PayoutAuthorisationResult;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class PayoutAuthorisationResultSerializer
{

    public static PayoutAuthorisationResult read(XmlBuilder builder)
    {
        PayoutAuthorisationResult result = null;

        if (builder.hasE("AuthorisationId"))
        {
            String authorizationId = builder.a("id");
            result = new PayoutAuthorisationResult(authorizationId);
            builder.up();
        }

        return result;
    }

}
