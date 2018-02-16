package com.worldpay.sdk.wpg.xml;

import com.jamesmurty.utils.XMLBuilder2;
import com.worldpay.sdk.wpg.response.Response;

import java.util.Map;

/**
 * Takes XML response and delegates it to the correct response handler class based on matching certain elements.
 */
public class XmlResponseRecognizer
{

    public Response match(Map<String, String> headers, String text)
    {
        XMLBuilder2 builder = XMLBuilder2.parse(text);
        // TODO
        return null;
    }

}
