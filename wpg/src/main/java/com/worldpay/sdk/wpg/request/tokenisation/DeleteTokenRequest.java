package com.worldpay.sdk.wpg.request.tokenisation;

import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.tokenisation.DeleteTokenSerializer;

public class DeleteTokenRequest extends XmlRequest<Void>
{
    private String paymentTokenId;
    private String shopperId;
    private String eventReference;
    private String reason;
    private TokenScope scope;

    /**
     * Sets up a request for a merchant token.
     *
     * @param paymentTokenId token identifier
     */
    public DeleteTokenRequest(String paymentTokenId)
    {
        this.paymentTokenId = paymentTokenId;
        this.scope = TokenScope.MERCHANT;
    }

    /**
     * Sets up a request for a shopper token.
     *
     * @param paymentTokenId token identifier
     * @param shopperId shopper identifier
     */
    public DeleteTokenRequest(String paymentTokenId, String shopperId)
    {
        this.paymentTokenId = paymentTokenId;
        this.shopperId = shopperId;
    }

    public DeleteTokenRequest(String paymentTokenId, String shopperId, String eventReference, String reason, TokenScope scope)
    {
        this.paymentTokenId = paymentTokenId;
        this.shopperId = shopperId;
        this.eventReference = eventReference;
        this.reason = reason;
        this.scope = scope;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        DeleteTokenSerializer.decorate(params, this);
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        XmlBuilder builder = response.getBuilder();

        if (!builder.hasE("reply") || !builder.hasE("ok") || !builder.hasE("deleteTokenReceived"))
        {
            throw new WpgMalformedResponseException(response);
        }
        return null;
    }

    public String getPaymentTokenId()
    {
        return paymentTokenId;
    }

    public DeleteTokenRequest paymentTokenId(String paymentTokenId)
    {
        this.paymentTokenId = paymentTokenId;
        return this;
    }

    public String getShopperId()
    {
        return shopperId;
    }

    public DeleteTokenRequest shopperId(String shopperId)
    {
        this.shopperId = shopperId;
        return this;
    }

    public String getEventReference()
    {
        return eventReference;
    }

    public DeleteTokenRequest eventReference(String eventReference)
    {
        this.eventReference = eventReference;
        return this;
    }

    public String getReason()
    {
        return reason;
    }

    public DeleteTokenRequest reason(String reason)
    {
        this.reason = reason;
        return this;
    }

    public TokenScope getScope()
    {
        return scope;
    }

    public DeleteTokenRequest scope(TokenScope scope)
    {
        this.scope = scope;
        return this;
    }

}
