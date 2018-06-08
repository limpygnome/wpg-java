package com.worldpay.sdk.demoshop.domain;

public class ApiOrderDetails
{
    private String orderCode;
    private String shopperId;
    private long amount;
    private String currency;
    private String description;
    private String address;
    private String city;
    private String postalCode;
    private String countryCode;
    private String userAgent;
    private String browserAccepts;
    private String ipAddress;

    public ApiOrderDetails()
    {
        amount = 0L;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getShopperId()
    {
        return shopperId;
    }

    public void setShopperId(String shopperId)
    {
        this.shopperId = shopperId;
    }

    public long getAmount()
    {
        return amount;
    }

    public void setAmount(long amount)
    {
        this.amount = amount;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getUserAgent()
    {
        return userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    public String getBrowserAccepts()
    {
        return browserAccepts;
    }

    public void setBrowserAccepts(String browserAccepts)
    {
        this.browserAccepts = browserAccepts;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

}
