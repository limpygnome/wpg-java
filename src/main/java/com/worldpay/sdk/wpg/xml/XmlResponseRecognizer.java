package com.worldpay.sdk.wpg.xml;

import com.worldpay.sdk.wpg.response.Response;

import java.util.Map;

/**
 * Takes XML response and delegates it to the correct response handler class based on matching certain elements.
 */
public class XmlResponseRecognizer
{

    public Response match(Map<String, String> headers, String text)
    {
        // TODO
        return null;
    }

}
