package com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation;

import com.worldpay.sdk.wpg.domain.tokenisation.Token;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenInstrument;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class TokenSerializer
{

    public static Token readElement(XmlBuilder builder) throws WpgRequestException
    {
        Token token = null;
        if (builder.hasE("token"))
        {
            token = read(builder);
        }
        return token;
    }

    public static Token read(XmlBuilder builder) throws WpgRequestException
    {
        Token token = null;

        String shopperId = builder.getCdata("authenticatedShopperID");
        TokenDetails details = TokenDetailsSerializer.read(builder);
        TokenInstrument instrument = TokenInstrumentSerializer.read(builder);

        if (shopperId != null || details != null || instrument != null)
        {
            token = new Token(details, instrument, shopperId);
        }

        return token;
    }

}
