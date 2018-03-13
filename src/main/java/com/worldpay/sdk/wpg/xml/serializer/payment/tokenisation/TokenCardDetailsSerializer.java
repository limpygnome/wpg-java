package com.worldpay.sdk.wpg.xml.serializer.payment.tokenisation;

import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenCardDetails;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.serializer.CardDetailsSerializer;

public class TokenCardDetailsSerializer
{

    public static TokenCardDetails read(XmlBuilder builder) throws WpgRequestException
    {
        TokenCardDetails result = null;

        if (builder.hasE("paymentInstrument"))
        {
            if (builder.hasE("cardDetails"))
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

                result = new TokenCardDetails(cardBrand, cardSubBrand, issuerCountryCode, obfuscatedPAN, cardDetails);
            }
            builder.up();
        }

        return result;
    }

}
