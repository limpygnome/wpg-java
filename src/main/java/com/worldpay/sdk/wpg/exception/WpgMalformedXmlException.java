package com.worldpay.sdk.wpg.exception;

public class WpgMalformedXmlException extends WpgException
{
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

    /**
     * @return the text attempted to be parsed
     */
    public String getText()
    {
        return text;
    }
}
