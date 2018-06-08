package com.worldpay.sdk.demoshop.domain;

public class ApiHppDetails
{
    private String successUrl;
    private String pendingUrl;
    private String failureUrl;
    private String errorUrl;
    private String cancelUrl;
    private String paymentMethod;

    public ApiHppDetails() { }

    public String getSuccessUrl()
    {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl)
    {
        this.successUrl = successUrl;
    }

    public String getPendingUrl()
    {
        return pendingUrl;
    }

    public void setPendingUrl(String pendingUrl)
    {
        this.pendingUrl = pendingUrl;
    }

    public String getFailureUrl()
    {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl)
    {
        this.failureUrl = failureUrl;
    }

    public String getErrorUrl()
    {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl)
    {
        this.errorUrl = errorUrl;
    }

    public String getCancelUrl()
    {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl)
    {
        this.cancelUrl = cancelUrl;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

}
