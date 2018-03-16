package com.worldpay.sdk.wpg.internal.xml.serializer.payment.conversion;

import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.conversion.CurrencyConversionRequired;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.serializer.AmountSerializer;

public class CurrencyConversionSerializer
{
    public static CurrencyConversionRequired read(XmlBuilder builder) throws WpgRequestException
    {
        Amount amount = AmountSerializer.read(builder.e("amount"));
        CurrencyConversionRequired currencyConversionRequired = new CurrencyConversionRequired(amount);
        return currencyConversionRequired;
    }
}
