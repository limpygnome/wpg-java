package com.worldpay.sdk.wpg.response.threeds;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.response.ResponseType;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.XmlResponse;

public class ThreeDsRequestedResponse extends XmlResponse
{
    private final String issuerURL;
    private final String paRequest;

    public ThreeDsRequestedResponse(HttpResponse httpResponse, XmlBuilder builder)
    {
        super(httpResponse, builder);

        this.issuerURL = builder.getCdata("issuerURL");
        this.paRequest = builder.getCdata("paRequest");
    }

    public String getIssuerURL()
    {
        return issuerURL;
    }

    public String getPaRequest()
    {
        return paRequest;
    }

    public ThreeDsRedirectBuilder createRedirectPage()
    {
        return new ThreeDsRedirectBuilder(this);
    }

    @Override
    public ResponseType getResponseType()
    {
        return ResponseType.THREEDS_REQUESTED;
    }

}
