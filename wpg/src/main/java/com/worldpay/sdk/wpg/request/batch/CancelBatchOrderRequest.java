package com.worldpay.sdk.wpg.request.batch;

import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;

/**
 * Cancels a batch modification request.
 *
 * <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/batchedmodifications.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/batchedmodifications.htm</a>
 */
public class CancelBatchOrderRequest extends XmlRequest<Void>
{
    private String id;

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notEmpty(id, "Batch ID is mandatory");
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        XmlBuilder builder = params.xmlBuilder();
        builder.e("modify").e("batchModification").a("merchantBatchCode", id).e("cancel");
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        // Check we have the expected response
        XmlBuilder builder = response.getBuilder();

        if (!builder.hasE("modify") || !builder.hasE("batchModification") || !id.equals(builder.a("merchantBatchCode")) || !builder.hasE("cancel"))
        {
            throw new WpgMalformedResponseException(response);
        }

        return null;
    }

    /**
     * @param id The identifier of the batch to be cancelled
     * @return Current instance
     */
    public CancelBatchOrderRequest id(String id)
    {
        this.id = id;
        return this;
    }

    /**
     * @return The identifier of the batch to be cancelled
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id The identifier of the batch to be cancelled
     */
    public void setId(String id)
    {
        this.id = id;
    }

}
