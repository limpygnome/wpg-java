package com.worldpay.sdk.wpg.domain;

public class Session
{
    private String shopperIpAddress;

    public Session() { }

    public Session(String shopperIpAddress)
    {
        this.shopperIpAddress = shopperIpAddress;
    }

    public String getShopperIpAddress()
    {
        return shopperIpAddress;
    }

    public void setShopperIpAddress(String shopperIpAddress)
    {
        this.shopperIpAddress = shopperIpAddress;
    }

}
