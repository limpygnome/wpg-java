package com.worldpay.sdk.wpg.internal.xml.serializer.tokenisation;

import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.request.tokenisation.FetchTokenRequest;
import com.worldpay.sdk.wpg.request.tokenisation.FetchTokensByShopperRequest;

public class FetchTokenSerializer
{

    public static void decorate(XmlBuildParams params, FetchTokenRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();

        String scope = request.getScope().name().toLowerCase();
        String shopperId = request.getShopperId();
        String paymentTokenId = request.getPaymentTokenId();

        builder.e("inquiry")
                .e("paymentTokenInquiry").a("scope", scope);

        if (shopperId != null)
        {
            builder.e("authenticatedShopperID").cdata(shopperId).up();
        }

        builder.e("paymentTokenID").cdata(paymentTokenId).up();
    }

    public static void decorate(XmlBuildParams params, FetchTokensByShopperRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();

        String shopperId = request.getShopperId();

        builder.e("shopperTokenRetrieval")
                .e("authenticatedShopperID").cdata(shopperId).up();
    }

}
