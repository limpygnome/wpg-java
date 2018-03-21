package com.worldpay.sdk.wpg.domain.payment.result;

public class ThreeDSecureResult
{
    private String description;
    private String eci;
    private String cavv;

    public ThreeDSecureResult(String description, String eci, String cavv)
    {
        this.description = description;
        this.eci = eci;
        this.cavv = cavv;
    }

    public String getDescription()
    {
        return description;
    }

    public String getEci()
    {
        return eci;
    }

    public String getCavv()
    {
        return cavv;
    }

    @Override
    public String toString()
    {
        return "ThreeDSecureResult{" +
                "description='" + description + '\'' +
                ", eci='" + eci + '\'' +
                ", cavv='" + cavv + '\'' +
                '}';
    }
}
