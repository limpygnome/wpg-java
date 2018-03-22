package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.conversion.CurrencyConversionRequired;
import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsRequired;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.PaymentSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.conversion.CurrencyConversionSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.threeds.ThreeDsSerializer;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;

public class PaymentResponseXmlAdapter
{

    public PaymentResponse read(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        HttpResponse httpResponse = response.getResponse();
        String xml = httpResponse.getBody();
        XmlBuilder builder = XmlBuilder.parse(xml);

        PaymentResponse result = null;

        if (builder.isCurrentTag("paymentService"))
        {
            if (builder.hasE("reply"))
            {
                if (builder.hasE("orderStatus"))
                {
                    result = readOrderStatus(httpResponse, builder);
                }
                else if (builder.hasE("error"))
                {
                    handleError(httpResponse, builder);
                }
            }
        }

        if (result == null)
        {
            throw new WpgRequestException("Unable to handle server response: \n" + httpResponse.getBody());
        }

        return result;
    }

    private PaymentResponse readOrderStatus(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException, WpgErrorResponseException
    {
        PaymentResponse result = null;

        if (builder.hasE("error"))
        {
            handleError(httpResponse, builder);
        }
        else if (builder.hasE("requestInfo"))
        {
            if (builder.hasE("request3DSecure"))
            {
                ThreeDsRequired threeDsRequired = ThreeDsSerializer.read(builder);
                result = new PaymentResponse(threeDsRequired);
            }
        }
        else if (builder.hasE("fxApprovalRequired"))
        {
            CurrencyConversionRequired currencyConversionRequired = CurrencyConversionSerializer.read(builder);
            result = new PaymentResponse(currencyConversionRequired);
        }
        else if (builder.hasE("payment"))
        {
            Payment payment = PaymentSerializer.read(builder);
            result = new PaymentResponse(payment);
        }

        // check we have something
        if (result == null)
        {
            throw new WpgErrorResponseException(0, "Unable to handle response from gateway, not recognized", httpResponse);
        }

        return result;
    }

    private void handleError(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException, WpgErrorResponseException
    {
        long code = builder.aToLong("code");
        String message = builder.cdata();
        throw new WpgErrorResponseException(code, message, httpResponse);
    }

}
