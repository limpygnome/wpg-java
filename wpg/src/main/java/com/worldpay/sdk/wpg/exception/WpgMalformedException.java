package com.worldpay.sdk.wpg.exception;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;

public class WpgMalformedException extends WpgException
{
    private HttpResponse response;
    private String content;

    public WpgMalformedException(HttpResponse response)
    {
        super("Unexpected response from gateway");
        this.response = response;
    }

    public WpgMalformedException(String message)
    {
        super(message);
    }

    public WpgMalformedException(String message, HttpResponse response)
    {
        super(message);
        this.response = response;
    }

    public WpgMalformedException(String message, String content)
    {
        super(message);
        this.content = content;
    }

    /**
     * @return The HTTP response attempted to be parsed; not always available
     */
    public HttpResponse getResponse()
    {
        return response;
    }

    /**
     * @return The malformed content, when not parsing requests (such as order notifications)
     */
    public String getContent()
    {
        return content;
    }

}
