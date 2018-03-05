package com.worldpay.sdk.wpg.response.approval;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.response.ResponseType;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.XmlResponse;
import com.worldpay.sdk.wpg.xml.serializer.AmountSerializer;

public class CurrencyConversionResponse extends XmlResponse
{
    private final Amount amount;

    public CurrencyConversionResponse(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException
    {
        super(httpResponse, builder);
        amount = AmountSerializer.read(builder.e("amount"));
    }

    public Amount getAmount()
    {
        return amount;
    }

    @Override
    public ResponseType getResponseType()
    {
        return ResponseType.CURRENCY_CONVERSION_REQUESTED;
    }

}
