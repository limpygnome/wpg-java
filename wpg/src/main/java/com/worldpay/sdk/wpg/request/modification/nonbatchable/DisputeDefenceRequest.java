package com.worldpay.sdk.wpg.request.modification.nonbatchable;

import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.modification.OrderModificationSerializer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * Not yet supported.
 */
public class DisputeDefenceRequest extends XmlRequest<Void>
{
    private String orderCode;
    private String text;
    private String attachment;

    public DisputeDefenceRequest() { }

    public DisputeDefenceRequest(String orderCode, String text, String attachmentBase64Encoded)
    {
        this.orderCode = orderCode;
        this.text = text;
        this.attachment = attachmentBase64Encoded;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        // TODO attachment limit?
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderModificationSerializer.decorateForRequest(params, orderCode);
        OrderModificationSerializer.decorate(params, this);
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
    {
        return null;
    }

    public DisputeDefenceRequest orderCode(String orderCode)
    {
        this.orderCode = orderCode;
        return this;
    }

    public DisputeDefenceRequest text(String text)
    {
        this.text = text;
        return this;
    }

    public DisputeDefenceRequest attachment(String attachmentBase64Encoded)
    {
        this.attachment = attachmentBase64Encoded;
        return this;
    }

    public DisputeDefenceRequest attachment(File file) throws IOException
    {
        InputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.length());
        byte[] buffer = new byte[1024];
        int read;
        while ((read = inputStream.read(buffer)) > 0)
        {
            baos.write(buffer, 0, read);
        }
        byte[] rawBytes = baos.toByteArray();
        attachment(rawBytes);
        return this;
    }

    public DisputeDefenceRequest attachment(byte[] rawBytes)
    {
        this.attachment = Base64.getEncoder().encodeToString(rawBytes);
        return this;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public String getText()
    {
        return text;
    }

    public String getAttachment()
    {
        return attachment;
    }
}
