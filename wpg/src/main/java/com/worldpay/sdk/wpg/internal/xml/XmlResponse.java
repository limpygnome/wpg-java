package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;

public class XmlResponse
{
    private HttpResponse response;
    private XmlBuilder builder;

    public XmlResponse(HttpResponse response, XmlBuilder builder)
    {
        this.response = response;
        this.builder = builder;
    }

    public HttpResponse getResponse()
    {
        return response;
    }

    public XmlBuilder getBuilder()
    {
        return builder;
    }
}
