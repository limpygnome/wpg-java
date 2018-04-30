package com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation;

import com.worldpay.sdk.wpg.domain.tokenisation.TokenDetails;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.serializer.DateSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TokenDetailsSerializer
{
    public static TokenDetails read(XmlBuilder builder) throws WpgRequestException
    {
        TokenDetails tokenDetails = null;

        if (builder.hasE("tokenDetails"))
        {
            String tokenEvent = builder.a("tokenEvent");
            String paymentTokenId = builder.getCdata("paymentTokenID");

            builder.e("paymentTokenExpiry");
            LocalDateTime tokenExpiry = DateSerializer.readDateTime(builder);
            builder.up();

            String eventReference = builder.getCdata("tokenEventReference");
            String eventReason = builder.getCdata("tokenReason");

            tokenDetails = new TokenDetails(paymentTokenId, tokenExpiry, tokenEvent, eventReference, eventReason);
            builder.up();
        }

        return tokenDetails;
    }

}
