package com.worldpay.sdk.wpg.domain.payment.threeds;

import com.worldpay.sdk.wpg.builder.ThreeDsRedirectBuilder;

public class ThreeDsRequired
{
    private final String issuerURL;
    private final String paRequest;

    public ThreeDsRequired(String issuerURL, String paRequest)
    {
        this.issuerURL = issuerURL;
        this.paRequest = paRequest;
    }

    public String getIssuerURL()
    {
        return issuerURL;
    }

    public String getPaRequest()
    {
        return paRequest;
    }

    public ThreeDsRedirectBuilder createRedirectPage()
    {
        return new ThreeDsRedirectBuilder(this);
    }

}
