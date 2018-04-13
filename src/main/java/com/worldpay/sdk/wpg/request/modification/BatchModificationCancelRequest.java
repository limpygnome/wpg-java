package com.worldpay.sdk.wpg.request.modification;

import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.ErrorCodeAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.BatchOrderModificationSerializer;

public class BatchModificationCancelRequest extends XmlRequest<Void>
{
    private String batchCode;

    public BatchModificationCancelRequest() { }

    public BatchModificationCancelRequest(String batchCode)
    {
        this.batchCode = batchCode;
    }

    public BatchModificationCancelRequest batchCode(String batchCode)
    {
        this.batchCode = batchCode;
        return this;
    }

    public String getBatchCode()
    {
        return batchCode;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        BatchOrderModificationSerializer.decorateForCancel(params, this);
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        XmlBuilder builder = response.getBuilder();

        if (!builder.hasE("reply") || !builder.hasE("batchStatus") || "CANCELLED".equals(builder.a("status")))
        {
            throw new WpgMalformedResponseException(response);
        }

        return null;
    }
}
