package com.worldpay.sdk.wpg.request.modification.nonbatchable;

import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.OrderModificationSerializer;

/**
 * Not yet supported.
 */
public class IncreaseAuthorisationRequest extends XmlRequest<Void>
{
    private String orderCode;
    private Amount amount;

    public IncreaseAuthorisationRequest() { }

    public IncreaseAuthorisationRequest(String orderCode, Amount amount)
    {
        this.orderCode = orderCode;
        this.amount = amount;
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

    public IncreaseAuthorisationRequest orderCode(String orderCode)
    {
        this.orderCode = orderCode;
        return this;
    }

    public IncreaseAuthorisationRequest amount(Amount amount)
    {
        this.amount = amount;
        return this;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public Amount getAmount()
    {
        return amount;
    }

}
