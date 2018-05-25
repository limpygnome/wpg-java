package com.worldpay.sdk.wpg.internal.xml.serializer.tokenisation;

import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.request.tokenisation.DeleteTokenRequest;

public class DeleteTokenSerializer
{

    public static void decorate(XmlBuildParams params, DeleteTokenRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();

        String scope = request.getScope().name().toLowerCase();
        String shopperId = request.getShopperId();
        String eventReference = request.getEventReference();
        String reason = request.getReason();

        builder.e("modify")
                .e("paymentTokenDelete").a("tokenScope", scope)
                .e("paymentTokenID").cdata(request.getPaymentTokenId()).up();

        if (shopperId != null)
        {
            builder.e("authenticatedShopperID").cdata(request.getShopperId()).up();
        }

        if(eventReference != null)
        {
            builder.e("tokenEventReference").cdata(request.getEventReference()).up();
        }

        if (reason != null)
        {
            builder.e("tokenReason").cdata(request.getReason()).up();
        }
    }

}
