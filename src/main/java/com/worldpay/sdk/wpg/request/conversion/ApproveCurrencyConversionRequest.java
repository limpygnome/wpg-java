package com.worldpay.sdk.wpg.request.conversion;

import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;

public class ApproveCurrencyConversionRequest extends XmlRequest<PaymentResponse>
{
    private Amount approvedAmount;

    @Override
    protected void validate(XmlBuildParams params)
    {
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        // TODO what goes here? is this a "public" / internal API/
    }

    @Override
    protected PaymentResponse adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        PaymentResponseXmlAdapter adapter = new PaymentResponseXmlAdapter();
        PaymentResponse result = adapter.read(response);
        return result;
    }

}
