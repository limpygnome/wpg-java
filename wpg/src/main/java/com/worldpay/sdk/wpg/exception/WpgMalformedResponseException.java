package com.worldpay.sdk.wpg.exception;

import com.worldpay.sdk.wpg.internal.xml.XmlResponse;

/**
 * Thrown when an unexpected response comes from the WPG gateway.
 */
public class WpgMalformedResponseException extends WpgMalformedXmlException
{
    private XmlResponse response;

    public WpgMalformedResponseException(XmlResponse response)
    {
        super("Unexpected response", response);
    }
}
