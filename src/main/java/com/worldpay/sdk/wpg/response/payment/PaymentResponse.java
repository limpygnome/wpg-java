package com.worldpay.sdk.wpg.response.payment;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.domain.payment.ThreeDsResult;
import com.worldpay.sdk.wpg.domain.payment.result.AvsResult;
import com.worldpay.sdk.wpg.domain.payment.result.AvvResult;
import com.worldpay.sdk.wpg.domain.payment.result.CvcResult;
import com.worldpay.sdk.wpg.domain.payment.result.ISO8583Result;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.response.ResponseType;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.XmlResponse;
import com.worldpay.sdk.wpg.xml.serializer.AmountSerializer;

public class PaymentResponse extends XmlResponse
{
    private String paymentMethod;
    private Amount amount;
    private PaymentStatus lastEvent;

    private ISO8583Result iso8583Result;
    private ThreeDsResult threeDsResult;
    private AvsResult avsResult;
    private CvcResult cvcResult;
    private AvvResult avvResult;

    public PaymentResponse(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException
    {
        super(httpResponse, builder);

        builder.e("paymentService")
                .e("reply")
                .e("orderStatus")
                .e("payment");

        // amount
        builder.e("amount");
        AmountSerializer.read(builder);
        builder.up();

        // last event
        builder.e("lastEvent");
        lastEvent = PaymentStatus.valueOf(builder.cdata());
        builder.up();

        // authorisation id

        // issuer name/country-code

        // balance

        // instalments

        builder.reset();
    }

    @Override
    public ResponseType getResponseType()
    {
        return ResponseType.PAYMENT_STATUS;
    }

}
