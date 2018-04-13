package com.worldpay.sdk.wpg.domain;

public class Shopper
{
    private String email;
    private String ipAddress;
    private ShopperBrowser browser;
    private String shopperId;

    public Shopper(String email)
    {
        this(email, null, null);
    }

    public Shopper(String email, String ipAddress, ShopperBrowser browser)
    {
        this.email = email;
        this.ipAddress = ipAddress;
        this.browser = browser;
    }

    public Shopper(String email, String ipAddress, ShopperBrowser browser, String shopperId)
    {
        this.email = email;
        this.ipAddress = ipAddress;
        this.browser = browser;
        this.shopperId = shopperId;
    }

    public Shopper(String email, String shopperId)
    {
        this.email = email;
        this.shopperId = shopperId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public ShopperBrowser getBrowser()
    {
        return browser;
    }

    public void setBrowser(ShopperBrowser browser)
    {
        this.browser = browser;
    }

    public String getShopperId()
    {
        return shopperId;
    }

    public void setShopperId(String authenticatedShopperId)
    {
        this.shopperId = authenticatedShopperId;
    }

}
