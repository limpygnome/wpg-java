package com.worldpay.sdk.wpg.response.redirect;

public class RedirectUrlResponse
{
    private String url;

    public String getUrl()
    {
        return url;
    }

    public RedirectBuilder append()
    {
        return new RedirectBuilder();
    }

}
