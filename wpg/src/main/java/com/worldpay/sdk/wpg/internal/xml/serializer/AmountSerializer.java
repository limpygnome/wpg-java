package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.DebitCreditIndicator;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class AmountSerializer
{

    public static Amount read(XmlBuilder builder) throws WpgRequestException
    {
        String currencyCode = builder.a("currencyCode");
        long exponent = builder.aToLong("exponent");
        long value = builder.aToLong("value");
        String rawDebitCreditIndicator = builder.a("debitCreditIndicator");
        DebitCreditIndicator debitCreditIndicator = rawDebitCreditIndicator != null ? DebitCreditIndicator.valueOf(rawDebitCreditIndicator.toUpperCase()) : null;

        Amount amount = new Amount(currencyCode, exponent, value, debitCreditIndicator);
        return amount;
    }

    public static void write(XmlBuilder builder, Amount amount)
    {
        builder.e("amount")
                .a("currencyCode", amount.getCurrencyCode())
                .a("exponent", String.valueOf(amount.getExponent()))
                .a("value", String.valueOf(amount.getValue()))
                .up();
    }

}
