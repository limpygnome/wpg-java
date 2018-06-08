package com.worldpay.sdk.demoshop.domain;

public class ApiPayPalDetails
{
    private String successUrl;
    private String failureUrl;
    private String cancelUrl;
    private String languageCode;
    private String email;

    public ApiPayPalDetails() { }

    public String getSuccessUrl()
    {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl)
    {
        this.successUrl = successUrl;
    }

    public String getFailureUrl()
    {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl)
    {
        this.failureUrl = failureUrl;
    }

    public String getCancelUrl()
    {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl)
    {
        this.cancelUrl = cancelUrl;
    }

    public String getLanguageCode()
    {
        return languageCode;
    }

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
