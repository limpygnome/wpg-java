package com.worldpay.sdk.wpg.request.recurring;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.PayAsOrderSerializer;

/**
 * Not yet supported.
 *
 *
 * A request to setup recurring payments, using payment details from an existing order.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/recurringpayments.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/recurringpayments.htm</a>
 */
public class RecurringPaymentRequest extends XmlRequest<PaymentResponse>
{
    private OrderDetails orderDetails;
    // -- CVC is optional
    private String cvc;

    private String existingOrderCode;
    private String existingMerchantCode;
    private Amount existingAmount;

    public RecurringPaymentRequest(OrderDetails orderDetails, String cvc, String existingOrderCode, String existingMerchantCode)
    {
        this.orderDetails = orderDetails;
        this.cvc = cvc;
        this.existingOrderCode = existingOrderCode;
        this.existingMerchantCode = existingMerchantCode;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        PayAsOrderSerializer.decorate(params, this);
    }

    @Override
    protected PaymentResponse adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        PaymentResponseXmlAdapter adapter = new PaymentResponseXmlAdapter();
        PaymentResponse paymentResponse = adapter.read(response);
        return paymentResponse;
    }

    public OrderDetails getOrderDetails()
    {
        return orderDetails;
    }

    public RecurringPaymentRequest orderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
        return this;
    }

    public String getCvc()
    {
        return cvc;
    }

    public RecurringPaymentRequest cvc(String cvc)
    {
        this.cvc = cvc;
        return this;
    }

    public String getExistingOrderCode()
    {
        return existingOrderCode;
    }

    public RecurringPaymentRequest existingOrderCode(String existingOrderCode)
    {
        this.existingOrderCode = existingOrderCode;
        return this;
    }

    public String getExistingMerchantCode()
    {
        return existingMerchantCode;
    }

    public RecurringPaymentRequest existingMerchantCode(String existingMerchantCode)
    {
        this.existingMerchantCode = existingMerchantCode;
        return this;
    }

    public Amount getExistingAmount()
    {
        return existingAmount;
    }

    public RecurringPaymentRequest existingAmount(Amount existingAmount)
    {
        this.existingAmount = existingAmount;
        return this;
    }

}
