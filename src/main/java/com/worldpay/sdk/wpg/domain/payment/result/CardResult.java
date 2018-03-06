package com.worldpay.sdk.wpg.domain.payment.result;

import com.worldpay.sdk.wpg.domain.CardType;

public class CardResult
{
    private final String maskedCardNumber;
    private final String hashedCardNumber;
    private final Long expiryMonth;
    private final Long expiryYear;
    private final String issuerCountryCode;
    private final String issuerName;
    private final String cardHolderName;
    private final CardType type;

    public CardResult(String maskedCardNumber, String hashedCardNumber, Long expiryMonth, Long expiryYear, String issuerCountryCode, String issuerName, String cardHolderName, CardType type)
    {
        this.maskedCardNumber = maskedCardNumber;
        this.hashedCardNumber = hashedCardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.issuerCountryCode = issuerCountryCode;
        this.issuerName = issuerName;
        this.cardHolderName = cardHolderName;
        this.type = type;
    }

    public String getMaskedCardNumber()
    {
        return maskedCardNumber;
    }

    public String getHashedCardNumber()
    {
        return hashedCardNumber;
    }

    public Long getExpiryMonth()
    {
        return expiryMonth;
    }

    public Long getExpiryYear()
    {
        return expiryYear;
    }

    public String getIssuerCountryCode()
    {
        return issuerCountryCode;
    }

    public String getIssuerName()
    {
        return issuerName;
    }

    public String getCardHolderName()
    {
        return cardHolderName;
    }

    public CardType getType()
    {
        return type;
    }

}
