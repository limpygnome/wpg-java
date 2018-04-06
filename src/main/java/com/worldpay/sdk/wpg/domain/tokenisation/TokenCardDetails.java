package com.worldpay.sdk.wpg.domain.tokenisation;

import com.worldpay.sdk.wpg.domain.CardDetails;

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

    public String getCardBrand()
    {
        return cardBrand;
    }

    public String getCardSubBrand()
    {
        return cardSubBrand;
    }

    public String getIssuerCountryCode()
    {
        return issuerCountryCode;
    }

    public String getObfuscatedCardNumber()
    {
        return obfuscatedCardNumber;
    }

    public CardDetails getCardDetails()
    {
        return cardDetails;
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
