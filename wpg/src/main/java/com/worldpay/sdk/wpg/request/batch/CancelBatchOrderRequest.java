package com.worldpay.sdk.wpg.request.batch;

import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;

/**
 * Not yet supported.
 */
public class CancelBatchOrderRequest extends XmlRequest<Void>
{
    private String id;

    @Override
    protected void validate(XmlBuildParams params)
    {
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

    public CancelBatchOrderRequest id(String id)
    {
        this.id = id;
        return this;
    }

    public String getId()
    {
        return id;
    }

}
