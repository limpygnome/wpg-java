package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;

public class XmlBuildParams
{
    private final GatewayContext gatewayContext;
    private final SessionContext sessionContext;
    private XmlBuilder xmlBuilder;

    public XmlBuildParams(GatewayContext gatewayContext, SessionContext sessionContext, XmlBuilder xmlBuilder)
    {
        this.gatewayContext = gatewayContext;
        this.sessionContext = sessionContext;
        this.xmlBuilder = xmlBuilder;
    }

    public GatewayContext gatewayContext()
    {
        return gatewayContext;
    }

    public SessionContext sessionContext()
    {
        return sessionContext;
    }

    public XmlBuilder xmlBuilder()
    {
        return xmlBuilder;
    }

    public void setBuilder(XmlBuilder xmlBuilder)
    {
        this.xmlBuilder = xmlBuilder;
    }

}
