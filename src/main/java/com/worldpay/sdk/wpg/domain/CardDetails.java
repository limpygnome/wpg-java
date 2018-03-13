package com.worldpay.sdk.wpg.domain;

public class CardDetails
{
    private String cardNumber;
    private long expiryMonth;
    private long expiryYear;
    private String cardHolderName;
    private long cvc;
    private Address cardHolderAddress;
    private String encryptedCardNumber;

    public CardDetails(String cardNumber, long expiryMonth, long expiryYear, String cardHolderName)
    {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cardHolderName = cardHolderName;
    }

    public CardDetails(String cardNumber, long expiryMonth, long expiryYear, String cardHolderName, long cvc)
    {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cardHolderName = cardHolderName;
        this.cvc = cvc;
    }

    public CardDetails(String cardNumber, long expiryMonth, long expiryYear, String cardHolderName, long cvc, Address cardHolderAddress)
    {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cardHolderName = cardHolderName;
        this.cvc = cvc;
        this.cardHolderAddress = cardHolderAddress;
    }

    public CardDetails(String cardNumber, long expiryMonth, long expiryYear, String cardHolderName, long cvc, Address cardHolderAddress, String encryptedCardNumber)
    {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cardHolderName = cardHolderName;
        this.cvc = cvc;
        this.cardHolderAddress = cardHolderAddress;
        this.encryptedCardNumber = encryptedCardNumber;
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

    public String getCardHolderName()
    {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName)
    {
        this.cardHolderName = cardHolderName;
    }

    public long getCvc()
    {
        return cvc;
    }

    public void setCvc(long cvc)
    {
        this.cvc = cvc;
    }

    public Address getCardHolderAddress()
    {
        return cardHolderAddress;
    }

    public void setCardHolderAddress(Address cardHolderAddress)
    {
        this.cardHolderAddress = cardHolderAddress;
    }

    public String getEncryptedCardNumber()
    {
        return encryptedCardNumber;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber)
    {
        this.encryptedCardNumber = encryptedCardNumber;
    }

}
