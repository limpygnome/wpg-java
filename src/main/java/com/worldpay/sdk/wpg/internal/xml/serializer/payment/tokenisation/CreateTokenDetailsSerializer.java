package com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation;

import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

import java.time.LocalDateTime;

public class CreateTokenDetailsSerializer
{

    public static void decorate(XmlBuildParams params, CreateTokenDetails createTokenDetails)
    {
        XmlBuilder builder = params.xmlBuilder();

        if (createTokenDetails != null)
        {
            TokenScope scope = createTokenDetails.getScope();
            builder.e("submit").e("order").e("createToken").a("tokenScope", scope.name().toLowerCase());
            builder.e("tokenEventReference").cdata(createTokenDetails.getEventReference()).up();
            builder.e("tokenReason").cdata(createTokenDetails.getReason()).up();

            if (createTokenDetails.getShortLifeMins() > 0)
            {
                builder.e("shortLifeMins").cdata(createTokenDetails.getShortLifeMins());
            }
            else if (createTokenDetails.getExpiry() != null)
            {
                LocalDateTime dateTime = createTokenDetails.getExpiry();

                builder.e("paymentTokenExpiry").e("date");
                builder.a("dayOfMonth", dateTime.getDayOfMonth());
                builder.a("month", dateTime.getMonthValue());
                builder.a("year", dateTime.getYear());
                builder.a("hour", dateTime.getHour());
                builder.a("minute", dateTime.getMinute());
                builder.a("second", dateTime.getSecond());
                builder.up().up();
            }
        }
    }

}
