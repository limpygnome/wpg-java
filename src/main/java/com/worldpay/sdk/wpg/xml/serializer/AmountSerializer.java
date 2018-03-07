package com.worldpay.sdk.wpg.xml.serializer;

import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.DebitCreditIndicator;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class AmountSerializer
{

    public static Amount read(XmlBuilder builder) throws WpgRequestException
    {
        String currencyCode = builder.a("currencyCode");
        Currency currency = Currency.valueOf(currencyCode);
        long exponent = builder.aToLong("exponent");
        long value = builder.aToLong("value");
        String rawDebitCreditIndicator = builder.a("debitCreditIndicator");
        DebitCreditIndicator debitCreditIndicator = rawDebitCreditIndicator != null ? DebitCreditIndicator.valueOf(rawDebitCreditIndicator.toUpperCase()) : null;

        Amount amount = new Amount(currency, exponent, value, debitCreditIndicator);
        return amount;
    }

}
