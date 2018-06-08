package com.worldpay.sdk.demoshop.domain;

public class ApiCardDetails
{
    private String name;
    private String cardNumber;
    private long expiryMonth;
    private long expiryYear;
    private String cvc;
    private boolean tokenise;

    public ApiCardDetails() { }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public long getExpiryMonth()
    {
        return expiryMonth;
    }

    public void setExpiryMonth(long expiryMonth)
    {
        this.expiryMonth = expiryMonth;
    }

    public long getExpiryYear()
    {
        return expiryYear;
    }

    public void setExpiryYear(long expiryYear)
    {
        this.expiryYear = expiryYear;
    }

    public String getCvc()
    {
        return cvc;
    }

    public void setCvc(String cvc)
    {
        this.cvc = cvc;
    }

    public boolean isTokenise()
    {
        return tokenise;
    }

    public void setTokenise(boolean tokenise)
    {
        this.tokenise = tokenise;
    }
}
