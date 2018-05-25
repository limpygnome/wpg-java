package com.worldpay.sdk.wpg.request.tokenisation;

import com.worldpay.sdk.wpg.domain.tokenisation.Token;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.TokenInquiryAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.tokenisation.FetchTokenSerializer;

import java.util.List;

/**
 * Not yet supported.
 */
public class FetchTokensByShopperRequest extends XmlRequest<List<Token>>
{
    private String shopperId;

    public FetchTokensByShopperRequest() { }

    public FetchTokensByShopperRequest(String shopperId)
    {
        this.shopperId = shopperId;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notEmpty(shopperId, "Shopper ID is mandatory");
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        FetchTokenSerializer.decorate(params, this);
    }

    @Override
    protected List<Token> adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        List<Token> result = TokenInquiryAdapter.readShopperTokens(response);
        return result;
    }

    public String getShopperId()
    {
        return shopperId;
    }

    public FetchTokensByShopperRequest shopperId(String shopperId)
    {
        this.shopperId = shopperId;
        return this;
    }

}
