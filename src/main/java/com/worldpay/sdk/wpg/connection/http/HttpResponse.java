package com.worldpay.sdk.wpg.connection.http;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The raw HTTP response from an API call.
 */
public class HttpResponse
{
    private final Map<String, String> headers;
    private final String body;

    public HttpResponse(byte[] data)
    {
        try
        {
            // convert to text
            String fullText = new String(data, "UTF-8");

            // find header/body separator
            int headerSplit = fullText.indexOf("\r\n\r\n");

            // read headers
            String rawHeader = headerSplit > 0 ? fullText.substring(0, headerSplit) : "";
            headers = readHeaders(rawHeader);

            // read body
            body = headerSplit > 0 && fullText.length() - (headerSplit + 4) > 0 ? fullText.substring(headerSplit + 4) : "";
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("UTF-8 encoding not supported", e);
        }
    }

    private Map<String, String> readHeaders(String headers)
    {
        Map<String, String> result = new HashMap<>();
        String[] lines = headers.split("\r\n");
        for (String line : lines)
        {
            int sep = line.indexOf(":");
            if (sep > 0 && sep < line.length()+1)
            {
                String key = line.substring(0, sep).trim();
                String value = line.substring(sep + 1).trim();
                result.put(key, value);
            }
        }
        return Collections.unmodifiableMap(result);
    }

    public Map<String, String> getHeaders()
    {
        return headers;
    }

    public String getHeader(String key)
    {
        return headers.get(key);
    }

    public Long getHeaderAsLong(String key)
    {
        String value = getHeader(key);
        Long result = null;

        try
        {
            if (value != null)
            {
                result = Long.parseLong(value);
            }
        }
        catch (NumberFormatException e)
        {
            // No need to handle, probably corrupt data
        }

        return result;
    }

    public String getBody()
    {
        return body;
    }

}
