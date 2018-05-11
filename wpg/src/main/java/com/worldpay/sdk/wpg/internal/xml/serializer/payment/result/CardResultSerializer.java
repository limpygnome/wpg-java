package com.worldpay.sdk.wpg.internal.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.CardType;
import com.worldpay.sdk.wpg.domain.payment.result.CardDetails;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class CardResultSerializer
{

    public static CardDetails read(XmlBuilder builder) throws WpgRequestException
    {
        String maskedCardNumber = null;
        String hashedCardNumber = null;
        Long expiryMonth = null;
        Long expiryYear = null;

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
        String issuerCountryCode = builder.getCdata("issuerCountryCode");
        String issuerName = builder.getCdata("issuerName");
        String cardHolderName = builder.getCdata("cardHolderName");

        // Check whether anything was found, otherwise return no result at all
        CardDetails result;

        if (maskedCardNumber != null || hashedCardNumber != null || expiryMonth != null || expiryYear != null
                || issuerCountryCode != null || issuerName != null || cardHolderName != null
                || type != null)
        {
            result = new CardDetails(maskedCardNumber, hashedCardNumber, expiryMonth, expiryYear,
                    issuerCountryCode, issuerName, cardHolderName, type);
        }
        else
        {
            result = null;
        }

        return result;
    }

}
