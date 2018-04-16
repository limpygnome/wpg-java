package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * Results from performing risk checks.
 */
public class RiskScoreResult
{
    private final String id;
    private final String provider;
    private final Integer value;
    private final Integer finalScore;
    private final Integer totalScore;
    private final Integer totalRisk;
    private final String riskGuardianId;
    private final String message;
    private final String extendedResponse;

    public RiskScoreResult(String id, String provider, Integer value, Integer finalScore, Integer totalScore, Integer totalRisk, String riskGuardianId, String message, String extendedResponse)
    {
        this.id = id;
        this.provider = provider;
        this.value = value;
        this.finalScore = finalScore;
        this.totalScore = totalScore;
        this.totalRisk = totalRisk;
        this.riskGuardianId = riskGuardianId;
        this.message = message;
        this.extendedResponse = extendedResponse;
    }

    public String getId()
    {
        return id;
    }

    public String getProvider()
    {
        return provider;
    }

    public Integer getValue()
    {
        return value;
    }

    public Integer getFinalScore()
    {
        return finalScore;
    }

    public Integer getTotalScore()
    {
        return totalScore;
    }

    public Integer getTotalRisk()
    {
        return totalRisk;
    }

    public String getRiskGuardianId()
    {
        return riskGuardianId;
    }

    public String getMessage()
    {
        return message;
    }

    public String getExtendedResponse()
    {
        return extendedResponse;
    }

    @Override
    public String toString()
    {
        return "RiskScoreResult{" +
                "id='" + id + '\'' +
                ", provider='" + provider + '\'' +
                ", value=" + value +
                ", finalScore=" + finalScore +
                ", totalScore=" + totalScore +
                ", totalRisk=" + totalRisk +
                ", riskGuardianId='" + riskGuardianId + '\'' +
                ", message='" + message + '\'' +
                ", extendedResponse='" + extendedResponse + '\'' +
                '}';
    }
}
