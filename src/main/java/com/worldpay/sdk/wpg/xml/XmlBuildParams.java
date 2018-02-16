package com.worldpay.sdk.wpg.xml;

import com.jamesmurty.utils.XMLBuilder2;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;

public class XmlBuildParams
{
    private final GatewayContext gatewayContext;
    private final SessionContext sessionContext;
    private final XMLBuilder2 xmlBuilder2;

    public XmlBuildParams(GatewayContext gatewayContext, SessionContext sessionContext, XMLBuilder2 xmlBuilder2)
    {
        this.gatewayContext = gatewayContext;
        this.sessionContext = sessionContext;
        this.xmlBuilder2 = xmlBuilder2;
    }

    public GatewayContext gatewayContext()
    {
        return gatewayContext;
    }

    public SessionContext sessionContext()
    {
        return sessionContext;
    }

    public XMLBuilder2 xmlBuilder2()
    {
        return xmlBuilder2;
    }

}
