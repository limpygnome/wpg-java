package com.worldpay.sdk.wpg.request.modification;

import com.worldpay.sdk.wpg.builder.RandomIdentifier;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.XmlService;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.BatchOrderModificationSerializer;
import com.worldpay.sdk.wpg.request.modification.batchable.BatchModificationItem;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * A request to submit multiple order modifications as a single job.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/batchedmodifications.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/batchedmodifications.htm</a>
 */
public class BatchModificationRequest extends XmlRequest<Void>
{
    private static final int BATCH_CODE_MAX_LENGTH = 25;

    private String batchCode;
    private List<BatchModificationItem> items;

    public BatchModificationRequest()
    {
        this.batchCode = RandomIdentifier.generate(BATCH_CODE_MAX_LENGTH);
        this.items = new LinkedList<>();
    }

    public BatchModificationRequest(List<BatchModificationItem> items)
    {
        this.items = items;
    }

    public synchronized BatchModificationRequest add(BatchModificationItem... items)
    {
        setupList();
        for (BatchModificationItem item : items)
        {
            this.items.add(item);
        }
        return this;
    }

    public synchronized BatchModificationRequest setItems(List<BatchModificationItem> items)
    {
        this.items = items;
        return this;
    }

    public synchronized List<BatchModificationItem> getItems()
    {
        return items;
    }

    public BatchModificationRequest batchCode(String batchCode)
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
        // TODO need to run validate on items
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        BatchOrderModificationSerializer.decorate(params, this);
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        // JUst check response is as expected
        XmlBuilder builder = response.getBuilder();

        if (!builder.hasE("ok"))
        {
            throw new WpgMalformedResponseException(response);
        }

        return null;
    }

    @Override
    protected XmlService getService()
    {
        return XmlService.BATCH;
    }

    private void setupList()
    {
        if (items == null)
        {
            items = new LinkedList<>();
        }
    }


}
