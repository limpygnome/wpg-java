package com.worldpay.sdk.wpg.xml;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.response.Response;

import java.util.Collections;
import java.util.Map;

public class XmlResponse implements Response<XmlBuilder>
{
    protected final HttpResponse httpResponse;
    protected final XmlBuilder builder;

    protected XmlResponse(HttpResponse httpResponse, XmlBuilder builder)
    {
        this.httpResponse = httpResponse;
        this.builder = builder;
    }

    @Override
    public HttpResponse getHttpResponse()
    {
        return httpResponse;
    }

    @Override
    public XmlBuilder getObject()
    {
        return builder;
    }

}
