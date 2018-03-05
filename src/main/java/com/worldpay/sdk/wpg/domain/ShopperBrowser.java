package com.worldpay.sdk.wpg.domain;

public class ShopperBrowser
{
    private String acceptHeader;
    private String userAgentHeader;
    private String httpAcceptLanguageHeader; // ??
    private String httpReferer;
    private String activeHeaders; // ?
    private String timeZone; // ?
    private String resolution; // ?

    public ShopperBrowser() { }

    public ShopperBrowser(String acceptHeader, String userAgentHeader)
    {
        this.acceptHeader = acceptHeader;
        this.userAgentHeader = userAgentHeader;
    }

    public ShopperBrowser(String acceptHeader, String userAgentHeader, String httpAcceptLanguageHeader, String httpReferer, String activeHeaders, String timeZone, String resolution)
    {
        this.acceptHeader = acceptHeader;
        this.userAgentHeader = userAgentHeader;
        this.httpAcceptLanguageHeader = httpAcceptLanguageHeader;
        this.httpReferer = httpReferer;
        this.activeHeaders = activeHeaders;
        this.timeZone = timeZone;
        this.resolution = resolution;
    }

    public String getAcceptHeader()
    {
        return acceptHeader;
    }

    public String getUserAgentHeader()
    {
        return userAgentHeader;
    }

}
