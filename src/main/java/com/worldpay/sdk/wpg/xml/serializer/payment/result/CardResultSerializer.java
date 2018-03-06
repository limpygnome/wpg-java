package com.worldpay.sdk.wpg.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.CardType;
import com.worldpay.sdk.wpg.domain.payment.result.CardResult;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class CardResultSerializer
{

    public static CardResult read(XmlBuilder builder) throws WpgRequestException
    {
        String maskedCardNumber = null;
        String hashedCardNumber = null;
        Long expiryMonth = null;
        Long expiryYear = null;

        String issuerCountryCode = null;
        String issuerName = null;
        String cardHolderName = null;
        CardType type = null;

        // payment detail (currently cards only) (not always enabled)
        if (builder.hasE("paymentMethodDetail"))
        {
            if (builder.hasE("card"))
            {
                maskedCardNumber = builder.a("number");
                hashedCardNumber = builder.a("hash");

                if (builder.hasE("expiryDate"))
                {
                    if (builder.hasE("date"))
                    {
                        expiryMonth = builder.aToLong("month");
                        expiryYear = builder.aToLong("year");
                        builder.up();
                    }
                    builder.up();
                }
                builder.up();
            }
            builder.up();
        }

        // issuer
        if (builder.hasE("issuerCountryCode"))
        {
            issuerCountryCode = builder.cdata();
            builder.up();
        }

        if (builder.hasE("issuerName"))
        {
            issuerName = builder.cdata();
            builder.up();
        }

        // card holder name
        if (builder.hasE("cardHolderName"))
        {
            cardHolderName = builder.cdata();
            builder.up();
        }

        // Check whether anything was found, otherwise return no result at all
        CardResult result;

        if (maskedCardNumber != null || hashedCardNumber != null || expiryMonth != null || expiryYear != null
                || issuerCountryCode != null || issuerName != null || cardHolderName != null
                || type != null)
        {
            result = new CardResult(maskedCardNumber, hashedCardNumber, expiryMonth, expiryYear,
                    issuerCountryCode, issuerName, cardHolderName, type);
        }
        else
        {
            result = null;
        }

        return result;
    }

}
