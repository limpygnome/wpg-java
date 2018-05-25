package com.worldpay.sdk.wpg.domain.tokenisation;

import com.worldpay.sdk.wpg.domain.CardDetails;

/**
 * Card details which have been tokenised / stored on our gateway for future payments.
 */
public class TokenCardDetails implements TokenInstrument
{
    private final String cardBrand;
    private final String cardSubBrand;
    private final String issuerCountryCode;
    private final String obfuscatedCardNumber;
    private final CardDetails cardDetails;

    public TokenCardDetails(String cardBrand, String cardSubBrand, String issuerCountryCode, String obfuscatedCardNumber, CardDetails cardDetails)
    {
        this.cardBrand = cardBrand;
        this.cardSubBrand = cardSubBrand;
        this.issuerCountryCode = issuerCountryCode;
        this.obfuscatedCardNumber = obfuscatedCardNumber;
        this.cardDetails = cardDetails;
    }

    /**
     * @return The card brand e.g. VISA, ECMC (Mastercard), AMEX
     */
    public String getCardBrand()
    {
        return cardBrand;
    }

    /**
     * @return The card sub-brand e.g. VISA-CREDIT
     */
    public String getCardSubBrand()
    {
        return cardSubBrand;
    }

    /**
     * @return The country code of the issuer
     */
    public String getIssuerCountryCode()
    {
        return issuerCountryCode;
    }

    /**
     * @return The masked card number
     */
    public String getObfuscatedCardNumber()
    {
        return obfuscatedCardNumber;
    }

    /**
     * @return The card details used to carry out the payment (may not always be available)
     */
    public CardDetails getCardDetails()
    {
        return cardDetails;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenCardDetails that = (TokenCardDetails) o;

        if (cardBrand != null ? !cardBrand.equals(that.cardBrand) : that.cardBrand != null) return false;
        if (cardSubBrand != null ? !cardSubBrand.equals(that.cardSubBrand) : that.cardSubBrand != null) return false;
        if (issuerCountryCode != null ? !issuerCountryCode.equals(that.issuerCountryCode) : that.issuerCountryCode != null)
            return false;
        if (obfuscatedCardNumber != null ? !obfuscatedCardNumber.equals(that.obfuscatedCardNumber) : that.obfuscatedCardNumber != null)
            return false;
        return cardDetails != null ? cardDetails.equals(that.cardDetails) : that.cardDetails == null;
    }

    @Override
    public int hashCode()
    {
        int result = cardBrand != null ? cardBrand.hashCode() : 0;
        result = 31 * result + (cardSubBrand != null ? cardSubBrand.hashCode() : 0);
        result = 31 * result + (issuerCountryCode != null ? issuerCountryCode.hashCode() : 0);
        result = 31 * result + (obfuscatedCardNumber != null ? obfuscatedCardNumber.hashCode() : 0);
        result = 31 * result + (cardDetails != null ? cardDetails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "TokenCardDetails{" +
                "cardBrand='" + cardBrand + '\'' +
                ", cardSubBrand='" + cardSubBrand + '\'' +
                ", issuerCountryCode='" + issuerCountryCode + '\'' +
                ", obfuscatedCardNumber='" + obfuscatedCardNumber + '\'' +
                ", cardDetails=" + cardDetails +
                '}';
    }
}
