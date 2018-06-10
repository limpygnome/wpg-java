package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsDetails;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.PaymentSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.threeds.ThreeDsSerializer;

public class PaymentResponseXmlAdapter
{

    public PaymentResponse read(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
    {
        HttpResponse httpResponse = response.getResponse();
        XmlBuilder builder =  response.getBuilder();

        PaymentResponse result = null;

        if (builder.isCurrentTag("paymentService"))
        {
            if (builder.hasE("reply") && builder.hasE("orderStatus"))
            {
                result = readOrderStatus(httpResponse, builder);
            }
        }

        if (result == null)
        {
            throw new WpgMalformedException(response.getResponse());
        }

        return result;
    }

    private PaymentResponse readOrderStatus(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException, WpgErrorResponseException
    {
        PaymentResponse result = null;

        if (builder.hasE("requestInfo"))
        {
            if (builder.hasE("request3DSecure"))
            {
                ThreeDsDetails threeDsDetails = ThreeDsSerializer.read(builder);
                result = new PaymentResponse(threeDsDetails);
            }
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

}
