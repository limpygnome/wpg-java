package com.worldpay.sdk.wpg.exception;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;

public class WpgErrorResponseException extends WpgException
{
    private final long gatewayErrorCode;
    private final String gatewayErrorMessage;
    private final HttpResponse httpResponse;

    public WpgErrorResponseException(long gatewayErrorCode, String message, HttpResponse httpResponse)
    {
        super("Gateway error - code: " + gatewayErrorCode + ", message: " + message);

        this.gatewayErrorCode = gatewayErrorCode;
        this.gatewayErrorMessage = message;
        this.httpResponse = httpResponse;
    }

    public long getGatewayErrorCode()
    {
        return gatewayErrorCode;
    }

    public String getGatewayErrorMessage()
    {
        return gatewayErrorMessage;
    }

    public HttpResponse getHttpResponse()
    {
        return httpResponse;
    }

}
