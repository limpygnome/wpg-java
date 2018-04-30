package com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation;

import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.serializer.DateSerializer;

import java.time.LocalDateTime;

public class CreateTokenDetailsSerializer
{

    public static void decorateOrder(XmlBuildParams params, CreateTokenDetails createTokenDetails)
    {
        XmlBuilder builder = params.xmlBuilder();

        if (createTokenDetails != null)
        {
            TokenScope scope = createTokenDetails.getScope();
            builder.e("createToken").a("tokenScope", scope.name().toLowerCase());
            builder.e("tokenEventReference").cdata(createTokenDetails.getEventReference()).up();
            builder.e("tokenReason").cdata(createTokenDetails.getReason()).up();

            if (createTokenDetails.getShortLifeMins() > 0)
            {
                builder.e("shortLifeMins").cdata(createTokenDetails.getShortLifeMins());
            }
            else if (createTokenDetails.getExpiry() != null)
            {
                builder.e("paymentTokenExpiry");

                LocalDateTime dateTime = createTokenDetails.getExpiry();
                DateSerializer.writeDateTime(builder, dateTime);

                builder.up();
            }

            // reset to order
            builder.up();
        }
    }

}
