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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RiskScoreResult that = (RiskScoreResult) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (provider != null ? !provider.equals(that.provider) : that.provider != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (finalScore != null ? !finalScore.equals(that.finalScore) : that.finalScore != null) return false;
        if (totalScore != null ? !totalScore.equals(that.totalScore) : that.totalScore != null) return false;
        if (totalRisk != null ? !totalRisk.equals(that.totalRisk) : that.totalRisk != null) return false;
        if (riskGuardianId != null ? !riskGuardianId.equals(that.riskGuardianId) : that.riskGuardianId != null)
            return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return extendedResponse != null ? extendedResponse.equals(that.extendedResponse) : that.extendedResponse == null;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (finalScore != null ? finalScore.hashCode() : 0);
        result = 31 * result + (totalScore != null ? totalScore.hashCode() : 0);
        result = 31 * result + (totalRisk != null ? totalRisk.hashCode() : 0);
        result = 31 * result + (riskGuardianId != null ? riskGuardianId.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (extendedResponse != null ? extendedResponse.hashCode() : 0);
        return result;
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
