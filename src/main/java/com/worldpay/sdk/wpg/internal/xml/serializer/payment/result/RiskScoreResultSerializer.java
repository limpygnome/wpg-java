package com.worldpay.sdk.wpg.internal.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.payment.result.RiskScoreResult;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class RiskScoreResultSerializer
{
    public static RiskScoreResult read(XmlBuilder builder) throws WpgRequestException
    {
        RiskScoreResult result = null;
        if (builder.hasE("riskScore"))
        {
            Integer value = builder.aToIntegerOptional("value");
            String provider = builder.a("provider");
            String id = builder.a("id");
            Integer finalScore = builder.aToIntegerOptional("finalScore");
            String riskGuardianId = builder.a("RGID");
            Integer totalScore = builder.aToIntegerOptional("tScore");
            Integer totalRisk = builder.aToIntegerOptional("tRisk");
            String message = builder.a("message");
            String extendedResponse = builder.a("extendedResponse");

            result = new RiskScoreResult(id, provider, value, finalScore, totalScore, totalRisk, riskGuardianId, message, extendedResponse);
            builder.up();
        }
        return result;
    }
}
