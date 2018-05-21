package com.worldpay.sdk.wpg.request.modification.nonbatchable;

import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.OrderModificationSerializer;

/**
 * Cancels an order.
 *
 * <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/modificationrequests.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/modificationrequests.htm</a>
 */
public class CancelOrderRequest extends XmlRequest<Void>
{
    private String orderCode;

    public CancelOrderRequest() { }

    public CancelOrderRequest(String orderCode)
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
        OrderModificationSerializer.decorateForRequest(params, orderCode);
        OrderModificationSerializer.decorate(params, this);
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        // Check expected response received
        XmlBuilder builder = response.getBuilder();
        if (!builder.hasE("reply") || !builder.hasE("ok") || !builder.hasE("cancelReceived") || !orderCode.equals(builder.a("orderCode")))
        {
            throw new WpgMalformedResponseException(response);
        }
        return null;
    }

    public CancelOrderRequest orderCode(String orderCode)
    {
        this.orderCode = orderCode;
        return this;
    }

    public String getOrderCode()
    {
        return orderCode;
    }
}
