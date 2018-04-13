package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.domain.tokenisation.Token;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation.TokenSerializer;

import java.util.ArrayList;
import java.util.List;

public class TokenInquiryAdapter
{
    public static Token readToken(XmlResponse response) throws WpgRequestException, WpgMalformedXmlException
    {
        Token token = null;
        XmlBuilder builder = response.getBuilder();

        if (builder.hasE("reply"))
        {
            token = TokenSerializer.read(builder);
        }

        if (token == null)
        {
            throw new WpgMalformedResponseException(response);
        }

        return token;
    }

    public static List<Token> readShopperTokens(XmlResponse response) throws WpgRequestException, WpgMalformedXmlException
    {
        XmlBuilder builder = response.getBuilder();
        builder.e("reply");

        List<XmlBuilder> elements = builder.childTags("token");

        // Check no other elements i.e. not malformed / unexpectd response
        if (elements.isEmpty() && builder.hasChildNodes())
        {
            throw new WpgMalformedResponseException(response);
        }

        // Read each token
        List<Token> tokens = new ArrayList<>(elements.size());
        for (XmlBuilder element : elements)
        {
            Token token = TokenSerializer.read(builder);
            tokens.add(token);
        }

        return tokens;
    }

}
