package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenisationPaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;

public class TokenisationPaymentResponseXmlAdapter
{

    public TokenisationPaymentResponse read(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        XmlBuilder builder = response.getBuilder();

        PaymentResponse paymentResponse = null;
        RedirectUrl captureCvc = null;

        // Attempt to parse
        if (builder.hasE("reply") && builder.hasE("orderStatus") && builder.hasE("reference"))
        {
            RedirectUrlXmlAdapter adapter = new RedirectUrlXmlAdapter();
            captureCvc = adapter.read(response);
        }
        else
        {
            builder.reset();

            PaymentResponseXmlAdapter adapter = new PaymentResponseXmlAdapter();
            paymentResponse = adapter.read(response);
        }

        if (captureCvc == null && paymentResponse == null)
        {
            throw new WpgMalformedResponseException(response);
        }

        TokenisationPaymentResponse result = new TokenisationPaymentResponse(paymentResponse, captureCvc);
        return result;
    }

}
