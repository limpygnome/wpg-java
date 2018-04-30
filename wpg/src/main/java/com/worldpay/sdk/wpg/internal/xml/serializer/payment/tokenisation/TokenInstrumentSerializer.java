package com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation;

import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenCardDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenInstrument;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.serializer.CardDetailsSerializer;

public class TokenInstrumentSerializer
{

    public static TokenInstrument read(XmlBuilder builder) throws WpgRequestException
    {
        TokenInstrument result = null;

        if (builder.hasE("paymentInstrument"))
        {
            if (builder.hasE("cardDetails"))
            {
                result = readCardDetails(builder);
                builder.up();
            }
            builder.up();
        }

        return result;
    }

    public static TokenInstrument readCardDetails(XmlBuilder builder) throws WpgRequestException
    {
        CardDetails cardDetails = CardDetailsSerializer.read(builder);
        String cardBrand = null;
        String cardSubBrand = null;
        String issuerCountryCode = null;
        String obfuscatedPAN = null;

        if (builder.hasE("derived"))
        {
            cardBrand = builder.getCdata("cardBrand");
            cardSubBrand = builder.getCdata("cardSubBrand");
            issuerCountryCode = builder.getCdata("issuerCountryCode");
            obfuscatedPAN = builder.getCdata("obfuscatedPAN");

            builder.up();
        }

        TokenInstrument result = new TokenCardDetails(cardBrand, cardSubBrand, issuerCountryCode, obfuscatedPAN, cardDetails);
        return result;
    }

}
