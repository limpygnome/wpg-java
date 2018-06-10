package com.worldpay.sdk.wpg.request.modification;

import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.XmlEndpoint;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.BatchOrderModificationSerializer;

/**
 * A request to cancel a batch modification job.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/batchedmodifications.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/batchedmodifications.htm</a>
 */
public class BatchModificationCancelRequest extends XmlRequest<Void>
{
    private String batchCode;

    public BatchModificationCancelRequest() { }

    public BatchModificationCancelRequest(String batchCode)
    {
        this.batchCode = batchCode;
    }

    /**
     * @param batchCode Identifier of the batch to be cancelled
     * @return Current instance
     */
    public BatchModificationCancelRequest batchCode(String batchCode)
    {
        this.batchCode = batchCode;
        return this;
    }

    /**
     * @return Identifier of batch being cancelled
     */
    public String getBatchCode()
    {
        return batchCode;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notEmpty(batchCode, "Batch code is mandatory");
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        BatchOrderModificationSerializer.decorateForCancel(params, this);
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
    {
        XmlBuilder builder = response.getBuilder();

        if (!builder.hasE("reply") || !builder.hasE("batchStatus") || !"CANCELLED".equals(builder.a("status")))
        {
            throw new WpgMalformedException(response.getResponse());
        }

        return null;
    }

    @Override
    protected XmlEndpoint getEndpoint()
    {
        return XmlEndpoint.PAYMENTS;
    }

}
