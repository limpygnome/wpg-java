package com.worldpay.sdk.wpg.domain.shopper;

public class Shopper
{
    private String email;
    private String ipAddress;
    private ShopperBrowser browser;
    private String shopperId;

    /**
     * @param browser Browser; mandatory for 3ds
     */
    public Shopper(ShopperBrowser browser)
    {
        this(null, null, browser, null);
    }

    /**
     * @param email E-mail; mandatory for some APMs
     */
    public Shopper(String email)
    {
        this(email, null, null);
    }

    /**
     * @param email Email; mandatory for some APMs
     * @param shopperId Shopper ID; mandatory for shopper tokens
     */
    public Shopper(String email, String shopperId)
    {
        this(email, null, null, shopperId);
    }

    /**
     * @param email E-mail; mandatory for some APMs
     * @param ipAddress IP address; optional but may impact risk management
     * @param browser
     */
    public Shopper(String email, String ipAddress, ShopperBrowser browser)
    {
        this(email, ipAddress, browser, null);
    }

    /**
     * @param email E-mail; mandatory for some APMs
     * @param ipAddress IP address; optional, but may impact risk management
     * @param browser Shopper's browser; required for 3ds
     * @param shopperId Shopper ID; mandatory for shopper tokens
     */
    public Shopper(String email, String ipAddress, ShopperBrowser browser, String shopperId)
    {
        this.email = email;
        this.ipAddress = ipAddress;
        this.browser = browser;
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
