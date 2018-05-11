package com.worldpay.sdk.wpg.request.tokenisation;

import com.worldpay.sdk.wpg.domain.tokenisation.Token;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation.TokenSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.tokenisation.FetchTokenSerializer;

/**
 * Not yet supported.
 */
public class FetchTokenRequest extends XmlRequest<Token>
{
    private String paymentTokenId;
    private String shopperId;
    private TokenScope scope;
    private boolean detokenise;

    public FetchTokenRequest(String paymentTokenId)
    {
        this.paymentTokenId = paymentTokenId;
    }

    public FetchTokenRequest(String paymentTokenId, String shopperId)
    {
        this.paymentTokenId = paymentTokenId;
        this.shopperId = shopperId;
    }

    public FetchTokenRequest(String paymentTokenId, String shopperId, TokenScope scope, boolean detokenise)
    {
        this.paymentTokenId = paymentTokenId;
        this.shopperId = shopperId;
        this.scope = scope;
        this.detokenise = detokenise;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        FetchTokenSerializer.decorate(params, this);
    }

    @Override
    protected Token adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        XmlBuilder builder = response.getBuilder();
        Token token = TokenSerializer.read(builder);
        return token;
    }

    public String getPaymentTokenId()
    {
        return paymentTokenId;
    }

    public FetchTokenRequest paymentTokenId(String paymentTokenId)
    {
        this.paymentTokenId = paymentTokenId;
        return this;
    }

    public String getShopperId()
    {
        return shopperId;
    }

    public FetchTokenRequest shopperId(String shopperId)
    {
        this.shopperId = shopperId;
        return this;
    }

    public TokenScope getScope()
    {
        return scope;
    }
    public FetchTokenRequest scope(TokenScope scope)
    {
        this.scope = scope;
        return this;
    }

    public boolean isDetokenise()
    {
        return detokenise;
    }

    public FetchTokenRequest detokenise(boolean detokenise)
    {
        this.detokenise = detokenise;
        return this;
    }


}
