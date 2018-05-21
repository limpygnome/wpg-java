package com.worldpay.sdk.wpg.request.modification.batchable;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.OrderModificationSerializer;

/**
 * Cancels a payment.
 *
 * This can only be done after a payment has been AUTHORISED, but not once it has reached CAPTURED.
 *
 * <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/modificationrequests.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/modificationrequests.htm</a>
 */
public class CancelOrRefundRequest extends XmlRequest<Void> implements BatchModificationItem
{
    private String orderCode;

    public CancelOrRefundRequest() { }

    public CancelOrRefundRequest(OrderDetails orderDetails)
    {
        Assert.notNull(orderDetails, "Order details cannot be null");
        this.orderCode = orderDetails.getOrderCode();
    }

    public CancelOrRefundRequest(String orderCode)
    {
        this.orderCode = orderCode;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderModificationSerializer.decorateForRequest(params, orderCode);
        OrderModificationSerializer.decorate(params, this);
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        return null;
    }

    public CancelOrRefundRequest orderCode(String orderCode)
    {
        this.orderCode = orderCode;
        return this;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

}
