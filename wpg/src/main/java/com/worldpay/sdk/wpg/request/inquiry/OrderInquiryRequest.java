package com.worldpay.sdk.wpg.request.inquiry;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderInquirySerializer;

/**
 * Makes a request to retrieve/inquire details about an order.
 *
 * The order should have a payment attempt against it.
 *
 * It's recommended to use order notifications instead.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/inquiryrequests.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/inquiryrequests.htm</a>
 */
public class OrderInquiryRequest extends XmlRequest<Payment>
{
    private String orderCode;

    public OrderInquiryRequest() { }

    public OrderInquiryRequest(OrderDetails orderDetails)
    {
        Assert.notNull(orderDetails, "Order details is null");

        this.orderCode = orderDetails.getOrderCode();
    }

    public OrderInquiryRequest(String orderCode)
    {
        this.orderCode = orderCode;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notEmpty(orderCode, "Order code is mandatory");
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderInquirySerializer.decorate(params, this);
    }

    @Override
    protected Payment adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
    {
        PaymentResponseXmlAdapter adapter = new PaymentResponseXmlAdapter();
        PaymentResponse result = adapter.read(response);
        Payment payment = result.getPayment();
        return payment;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public OrderInquiryRequest orderCode(String orderCode)
    {
        this.orderCode = orderCode;
        return this;
    }

}
