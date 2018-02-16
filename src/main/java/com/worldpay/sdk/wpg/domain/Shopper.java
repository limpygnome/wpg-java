package com.worldpay.sdk.wpg.domain;

public class Shopper
{
    private String email;
    private String shopperId;
    private ShopperBrowser browser;

    public Shopper(String email)
    {
        this(email, null, null);
    }

    public Shopper(String email, String shopperId, ShopperBrowser browser)
    {
        this.email = email;
        this.shopperId = shopperId;
        this.browser = browser;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getShopperId()
    {
        return shopperId;
    }

    public void setShopperId(String authenticatedShopperId)
    {
        this.shopperId = authenticatedShopperId;
    }

    public ShopperBrowser getBrowser()
    {
        return browser;
    }

    public void setBrowser(ShopperBrowser browser)
    {
        this.browser = browser;
    }
}
