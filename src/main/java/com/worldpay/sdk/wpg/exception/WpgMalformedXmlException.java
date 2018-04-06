package com.worldpay.sdk.wpg.exception;

import com.worldpay.sdk.wpg.internal.xml.XmlResponse;

public class WpgMalformedXmlException extends WpgException
{
    private XmlResponse response;
    private String text;

    public WpgMalformedXmlException(String text)
    {
        super("XML could not be parsed");
        this.text = text;
    }

    public WpgMalformedXmlException(String text, Throwable cause)
    {
        super("XML could not be parsed", cause);
        this.text = text;
    }

    public WpgMalformedXmlException(String message, XmlResponse response)
    {
        super(message);
        this.response = response;
    }

    /**
     * @return the text attempted to be parsed
     */
    public String getText()
    {
        return text;
    }

    public XmlResponse getResponse()
    {
        return response;
    }
}
