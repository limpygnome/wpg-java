package com.worldpay.sdk.wpg.internal.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.card.CardType;
import com.worldpay.sdk.wpg.domain.payment.result.CardDetailsResultResult;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class CardDetalsResultSerializer
{

    public static CardDetailsResultResult read(XmlBuilder builder) throws WpgRequestException
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

                String rawType = builder.a("type");
                if (rawType != null)
                {
                    switch (rawType)
                    {
                        case "creditcard":
                            type = CardType.CREDIT;
                            break;
                        case "debitcard":
                            type = CardType.DEBIT;
                            break;
                    }
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
        CardDetailsResultResult result;

        if (maskedCardNumber != null || hashedCardNumber != null || expiryMonth != null || expiryYear != null
                || issuerCountryCode != null || issuerName != null || cardHolderName != null
                || type != null)
        {
            result = new CardDetailsResultResult(maskedCardNumber, hashedCardNumber, expiryMonth, expiryYear,
                    issuerCountryCode, issuerName, cardHolderName, type);
        }
        else
        {
            result = null;
        }

        return result;
    }

}
