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

    /**
     * @return Masked card number
     */
    public String getMaskedCardNumber()
    {
        return maskedCardNumber;
    }

    /**
     * @return Hashed card number
     */
    public String getHashedCardNumber()
    {
        return hashedCardNumber;
    }

    /**
     * @return The month of when the card expires
     */
    public Long getExpiryMonth()
    {
        return expiryMonth;
    }

    /**
     * @return The year of when the card expires
     */
    public Long getExpiryYear()
    {
        return expiryYear;
    }

    /**
     * @return THe issuer's country code
     */
    public String getIssuerCountryCode()
    {
        return issuerCountryCode;
    }

    /**
     * @return The issuer's name
     */
    public String getIssuerName()
    {
        return issuerName;
    }

    /**
     * @return The card holder's name
     */
    public String getCardHolderName()
    {
        return cardHolderName;
    }

    /**
     * @return The type of card
     */
    public CardType getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return "CardResult{" +
                "maskedCardNumber='" + maskedCardNumber + '\'' +
                ", hashedCardNumber='" + hashedCardNumber + '\'' +
                ", expiryMonth=" + expiryMonth +
                ", expiryYear=" + expiryYear +
                ", issuerCountryCode='" + issuerCountryCode + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", type=" + type +
                '}';
    }

}
