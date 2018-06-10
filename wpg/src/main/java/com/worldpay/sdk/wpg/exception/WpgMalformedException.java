package com.worldpay.sdk.wpg.exception;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;

public class WpgMalformedException extends WpgException
{
    private HttpResponse response;

    public WpgMalformedException(HttpResponse response)
    {
        super("Unexpected data");
        this.response = response;
    }

    public WpgMalformedException(String message, HttpResponse response)
    {
        super(message);
        this.response = response;
    }

    /**
     * @return The HTTP response attempted to be parsed; not always available
     */
    public HttpResponse getResponse()
    {
        return response;
    }

}
