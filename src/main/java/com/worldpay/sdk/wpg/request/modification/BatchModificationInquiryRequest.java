package com.worldpay.sdk.wpg.request.modification;

import com.worldpay.sdk.wpg.domain.notification.BatchInquiry;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.BatchInquiryAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.BatchOrderModificationSerializer;

public class BatchModificationInquiryRequest extends XmlRequest<BatchInquiry>
{
    private String batchCode;

    public BatchModificationInquiryRequest() { }

    public BatchModificationInquiryRequest(String batchCode)
    {
        this.batchCode = batchCode;
    }

    public BatchModificationInquiryRequest batchCode(String batchCode)
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
        BatchOrderModificationSerializer.decorateForInquiry(params, this);
    }

    @Override
    protected BatchInquiry adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        BatchInquiry inquiry = BatchInquiryAdapter.read(response);
        return inquiry;
    }

}
