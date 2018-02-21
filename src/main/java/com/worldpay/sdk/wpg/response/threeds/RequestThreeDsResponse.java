package com.worldpay.sdk.wpg.response.threeds;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.XmlResponse;

import java.util.Map;

public class RequestThreeDsResponse extends XmlResponse
{
    private final String issuerURL;
    private final String paRequest;

    public RequestThreeDsResponse(HttpResponse httpResponse, XmlBuilder builder)
    {
        super(httpResponse, builder);

        this.issuerURL = builder.e("issuerURL").cdata();
        builder.up();

        this.paRequest = builder.e("paRequest").cdata();
        builder.up();
    }

    public String getIssuerURL()
    {
        return issuerURL;
    }

    public String getPaRequest()
    {
        return paRequest;
    }

}
