package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.adapter.ErrorCodeAdapter;

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
        XmlService service = getService();
        XmlBuilder builder = new XmlBuilder(service);
        XmlBuildParams params = new XmlBuildParams(gatewayContext, sessionContext, builder, service, isBatch());

        // validate and build request
        validate(params);
        build(params);

        // send request
        XmlClient client = new XmlClient();
        XmlResponse response = client.send(params);

        // check response doesn't have an error
        ErrorCodeAdapter.throwIfPresent(response);

        // adapt to type
        T result = adapt(response);
        return result;
    }

    protected void proxyBuild(XmlBuildParams params, XmlRequest request)
    {
        request.build(params);
    }

    protected void proxyValidate(XmlBuildParams params, XmlRequest request)
    {
        request.validate(params);
    }

    protected boolean isBatch()
    {
        return false;
    }

    protected XmlService getService()
    {
        return XmlService.PAYMENT;
    }

}
