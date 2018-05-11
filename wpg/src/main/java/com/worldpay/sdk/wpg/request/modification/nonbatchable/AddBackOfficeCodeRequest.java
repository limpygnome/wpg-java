package com.worldpay.sdk.wpg.request.modification.nonbatchable;

import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.ErrorCodeAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.OrderModificationSerializer;

/**
 * Not yet supported.
 */
public class AddBackOfficeCodeRequest extends XmlRequest<Void>
{
    private String orderCode;
    private String backOfficeCode;

    public AddBackOfficeCodeRequest() { }

    public AddBackOfficeCodeRequest(String orderCode, String backOfficeCode)
    {
        this.orderCode = orderCode;
        this.backOfficeCode = backOfficeCode;
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

    public AddBackOfficeCodeRequest orderCode(String orderCode)
    {
        this.orderCode = orderCode;
        return this;
    }

    public AddBackOfficeCodeRequest backOfficeCode(String backOfficeCode)
    {
        this.backOfficeCode = backOfficeCode;
        return this;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public String getBackOfficeCode()
    {
        return backOfficeCode;
    }

}
