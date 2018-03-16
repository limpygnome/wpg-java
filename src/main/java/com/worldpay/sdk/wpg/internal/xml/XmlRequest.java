package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;

public abstract class XmlRequest<T>
{
    protected abstract void validate(XmlBuildParams params);

    protected abstract void build(XmlBuildParams params);

    protected abstract T adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException;

    public T send(GatewayContext gatewayContext) throws WpgRequestException,
            WpgConnectionException, WpgErrorResponseException, WpgMalformedXmlException
    {
        return send(gatewayContext, new SessionContext());
    }

    public T send(GatewayContext gatewayContext, SessionContext sessionContext) throws WpgRequestException,
            WpgConnectionException, WpgErrorResponseException, WpgMalformedXmlException
    {
        XmlBuilder builder = XmlBuilder.create();
        XmlBuildParams params = new XmlBuildParams(gatewayContext, sessionContext, builder);

        // validate and build request
        validate(params);
        build(params);

        // send request
        XmlClient client = new XmlClient();
        XmlResponse response = client.send(params);

        // adapt response
        T result = adapt(response);
        return result;
    }

}
