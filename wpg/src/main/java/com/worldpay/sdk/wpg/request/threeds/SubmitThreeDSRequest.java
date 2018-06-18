package com.worldpay.sdk.wpg.request.threeds;

import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsDetails;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;

/**
 * Submits three-ds card authentication details to complete a payment.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm</a>
 */
public class SubmitThreeDSRequest extends XmlRequest<PaymentResponse>
{
    private SessionContext sessionContext;
    private String orderCode;
    private String paResponse;

    public SubmitThreeDSRequest() { }

    public SubmitThreeDSRequest(String orderCode, String paResponse)
    {
        this.orderCode = orderCode;
        this.paResponse = paResponse;
    }

    public SubmitThreeDSRequest(OrderDetails orderDetails, String paResponse)
    {
        this.orderCode = orderDetails.getOrderCode();
        this.paResponse = paResponse;
    }

    public SubmitThreeDSRequest(SessionContext sessionContext, OrderDetails orderDetails, String paResponse)
    {
        this.sessionContext = sessionContext;
        this.orderCode = orderDetails.getOrderCode();
        this.paResponse = paResponse;
    }

    public SubmitThreeDSRequest(ThreeDsDetails threeDsDetails, OrderDetails orderDetails, String paResponse)
    {
        this.sessionContext = threeDsDetails.getSessionContext();
        this.orderCode = orderDetails.getOrderCode();
        this.paResponse = paResponse;
    }

    public SubmitThreeDSRequest sessionContext(SessionContext sessionContext)
    {
        this.sessionContext = sessionContext;
        return this;
    }

    public SubmitThreeDSRequest orderCode(String orderCode)
    {
        this.orderCode = orderCode;
        return this;
    }

    public SubmitThreeDSRequest paResponse(String paResponse)
    {
        this.paResponse = paResponse;
        return this;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notEmpty(orderCode, "Order code is mandatory");
        Assert.notEmpty(paResponse, "PaResponse is mandatory");
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        XmlBuilder builder = params.xmlBuilder();

        UserPassAuth auth = (UserPassAuth) params.gatewayContext().getAuth();
        String sessionId = params.sessionContext().getSessionId();

        builder.a("merchantCode", auth.getMerchantCode())
                .e("submit")
                .e("order").a("orderCode", orderCode);

        if (auth.getInstallationId() != null && !auth.getInstallationId().isEmpty())
        {
            builder.a("installationId", auth.getInstallationId());
        }

        builder
                .e("info3DSecure")
                    .e("paResponse").cdata(paResponse).up()
                    .up()
                .e("session").a("id", sessionId);
    }

    @Override
    protected PaymentResponse adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
    {
        PaymentResponseXmlAdapter adapter = new PaymentResponseXmlAdapter();
        PaymentResponse result = adapter.read(response);
        return result;
    }

    /**
     * You must set session context.
     *
     * Otherwise use {@link #send(GatewayContext, SessionContext)}, passing in the previously used session context from
     * the initial card payment request.
     *
     * @param gatewayContext Gateway context
     * @return Payment response
     * @throws WpgRequestException
     * @throws WpgConnectionException
     * @throws WpgErrorResponseException
     * @throws WpgMalformedException
     */
    @Override
    public PaymentResponse send(GatewayContext gatewayContext) throws WpgRequestException, WpgConnectionException, WpgErrorResponseException, WpgMalformedException
    {
        if (sessionContext == null)
        {
            throw new IllegalStateException("SessionContext not set! Needs to be set, or use send(GatewayContext, CessionContext) - you need to pass session context from initial request");
        }
        return send(gatewayContext, sessionContext);
    }

}
