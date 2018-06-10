package com.worldpay.sdk.wpg.request.modification.batchable;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.OrderModificationSerializer;

/**
 * Captures a payment that is authorised.
 *
 * <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/modificationrequests.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/modificationrequests.htm</a>
 */
public class CaptureOrderRequest extends XmlRequest<Void> implements BatchModificationItem
{
    // Mandatory
    private String orderCode;
    private Amount amount;

    // Optional
    private String reference;

    public CaptureOrderRequest() { }

    public CaptureOrderRequest(OrderDetails orderDetails, String reference, Amount amount)
    {
        Assert.notNull(orderDetails, "Order details cannot be null");
        this.orderCode = orderDetails.getOrderCode();
        this.reference = reference;
        this.amount = amount;
    }

    public CaptureOrderRequest(String orderCode, String reference, Amount amount)
    {
        this.orderCode = orderCode;
        this.reference = reference;
        this.amount = amount;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notEmpty(orderCode, "Order code is mandatory");
        Assert.notNull(amount, "Reference is mandatory");
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderModificationSerializer.decorateForRequest(params, orderCode);
        OrderModificationSerializer.decorate(params, this);
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
    {
        return null;
    }

    public CaptureOrderRequest orderCode(String orderCode)
    {
        this.orderCode = orderCode;
        return this;
    }

    public CaptureOrderRequest reference(String reference)
    {
        this.reference = reference;
        return this;
    }

    public CaptureOrderRequest amount(Amount amount)
    {
        this.amount = amount;
        return this;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public String getReference()
    {
        return reference;
    }

    public Amount getAmount()
    {
        return amount;
    }

}
