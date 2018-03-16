package com.worldpay.sdk.wpg.domain.redirect;

import com.worldpay.sdk.wpg.builder.PaymentPagesRedirectBuilder;

import java.io.Serializable;

public class RedirectUrl implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final String url;

    public RedirectUrl(String url)
    {
        this.url = url;
    }

    public PaymentPagesRedirectBuilder paymentPages()
    {
        return new PaymentPagesRedirectBuilder(url);
    }

    public String getUrl()
    {
        return url;
    }
}
