package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.connection.http.HttpResponse;

public class XmlResponse
{
    private final SessionContext sessionContext;
    private final HttpResponse response;
    private final XmlBuilder builder;

    public XmlResponse(SessionContext sessionContext, HttpResponse response, XmlBuilder builder)
    {
        this.sessionContext = sessionContext;
        this.response = response;
        this.builder = builder;
    }

    public SessionContext getSessionContext()
    {
        return sessionContext;
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
