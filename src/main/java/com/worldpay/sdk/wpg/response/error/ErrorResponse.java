package com.worldpay.sdk.wpg.response.error;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.response.ResponseType;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.XmlResponse;

public class ErrorResponse extends XmlResponse
{
    private final long code;
    private final String message;

    public ErrorResponse(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException
    {
        super(httpResponse, builder);
        code = builder.aToLong("code");
        message = builder.cdata();
    }

    public long getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public ResponseType getResponseType()
    {
        return ResponseType.ERROR;
    }

}
