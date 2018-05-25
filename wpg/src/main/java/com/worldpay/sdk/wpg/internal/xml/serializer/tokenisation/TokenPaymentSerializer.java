package com.worldpay.sdk.wpg.internal.xml.serializer.tokenisation;

import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.request.tokenisation.TokenPaymentRequest;

public class TokenPaymentSerializer
{

    public static void decorateOrder(XmlBuildParams params, TokenPaymentRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();

        String scope = request.getScope().name().toLowerCase();

        builder.e("paymentDetails")
                .e("TOKEN-SSL")
                    .a("tokenScope", scope);

        if (request.isCaptureCvc())
        {
            builder.a("captureCvc", "true");
        }

        builder.e("paymentTokenID").cdata(request.getPaymentTokenId()).up();

        // reset to order element
        builder.up().up();
    }

}
