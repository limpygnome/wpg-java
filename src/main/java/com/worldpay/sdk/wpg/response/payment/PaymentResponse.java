package com.worldpay.sdk.wpg.response.payment;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.response.ResponseType;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.XmlResponse;
import com.worldpay.sdk.wpg.xml.serializer.payment.PaymentStatusSerializer;

public class PaymentResponse extends XmlResponse
{
    private Payment payment;

    public PaymentResponse(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException
    {
        super(httpResponse, builder);
        payment = PaymentStatusSerializer.read(builder);
    }

    @Override
    public ResponseType getResponseType()
    {
        return ResponseType.PAYMENT_STATUS;
    }

    public Payment getPayment()
    {
        return payment;
    }
}
