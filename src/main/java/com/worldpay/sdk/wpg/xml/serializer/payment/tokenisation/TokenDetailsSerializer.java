package com.worldpay.sdk.wpg.xml.serializer.payment.tokenisation;

import com.worldpay.sdk.wpg.domain.tokenisation.TokenDetails;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

import java.time.LocalDateTime;

public class TokenDetailsSerializer
{
    public static TokenDetails read(XmlBuilder builder) throws WpgRequestException
    {
        TokenDetails tokenDetails = null;

        if (builder.hasE("tokenDetails"))
        {
            String shopperId = builder.getCdata("authenticatedShopperID");

            String tokenEvent = builder.a("tokenEvent");
            String paymentTokenId = builder.getCdata("paymentTokenID");

            builder.e("paymentTokenExpiry");
            LocalDateTime tokenExpiry = readExpiry(builder);
            builder.up();

            String eventReference = builder.getCdata("tokenEventReference");
            String eventReason = builder.getCdata("tokenReason");

            tokenDetails = new TokenDetails(paymentTokenId, shopperId, tokenExpiry, tokenEvent, eventReference, eventReason);
        }

        return tokenDetails;
    }

    public static LocalDateTime readExpiry(XmlBuilder builder) throws WpgRequestException
    {
        // TODO what timezone is this? doesn't say in docs, this is appropriate for now
        int dayOfMonth = builder.aToInt("dayOfMonth");
        int month = builder.aToInt("month");
        int year = builder.aToInt("year");
        int hour = builder.aToInt("hour");
        int minute = builder.aToInt("minute");
        int second = builder.aToInt("second");

        LocalDateTime dateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        return dateTime;
    }

}
