package com.worldpay.sdk.wpg.domain.payment.result;

import com.worldpay.sdk.wpg.domain.card.CardType;

public class CardDetailsResultResult
{
    private final String maskedCardNumber;
    private final String hashedCardNumber;
    private final Long expiryMonth;
    private final Long expiryYear;
    private final String issuerCountryCode;
    private final String issuerName;
    private final String cardHolderName;
    private final CardType type;

    public CardDetailsResultResult(String maskedCardNumber, String hashedCardNumber, Long expiryMonth, Long expiryYear, String issuerCountryCode, String issuerName, String cardHolderName, CardType type)
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardDetailsResultResult that = (CardDetailsResultResult) o;

        if (maskedCardNumber != null ? !maskedCardNumber.equals(that.maskedCardNumber) : that.maskedCardNumber != null)
            return false;
        if (hashedCardNumber != null ? !hashedCardNumber.equals(that.hashedCardNumber) : that.hashedCardNumber != null)
            return false;
        if (expiryMonth != null ? !expiryMonth.equals(that.expiryMonth) : that.expiryMonth != null) return false;
        if (expiryYear != null ? !expiryYear.equals(that.expiryYear) : that.expiryYear != null) return false;
        if (issuerCountryCode != null ? !issuerCountryCode.equals(that.issuerCountryCode) : that.issuerCountryCode != null)
            return false;
        if (issuerName != null ? !issuerName.equals(that.issuerName) : that.issuerName != null) return false;
        if (cardHolderName != null ? !cardHolderName.equals(that.cardHolderName) : that.cardHolderName != null)
            return false;
        return type == that.type;
    }

    @Override
    public int hashCode()
    {
        int result = maskedCardNumber != null ? maskedCardNumber.hashCode() : 0;
        result = 31 * result + (hashedCardNumber != null ? hashedCardNumber.hashCode() : 0);
        result = 31 * result + (expiryMonth != null ? expiryMonth.hashCode() : 0);
        result = 31 * result + (expiryYear != null ? expiryYear.hashCode() : 0);
        result = 31 * result + (issuerCountryCode != null ? issuerCountryCode.hashCode() : 0);
        result = 31 * result + (issuerName != null ? issuerName.hashCode() : 0);
        result = 31 * result + (cardHolderName != null ? cardHolderName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "CardDetails{" +
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
