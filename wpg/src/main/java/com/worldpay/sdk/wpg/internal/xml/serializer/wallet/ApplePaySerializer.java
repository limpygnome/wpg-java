package com.worldpay.sdk.wpg.internal.xml.serializer.wallet;

import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.request.wallet.ApplePayRequest;

public class ApplePaySerializer
{

    public static void decorateOrder(XmlBuildParams params, ApplePayRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();

        builder.e("paymentDetails")
                .e("APPLEPAY-SSL");

        // Header
        builder.e("header");
        builder.e("ephemeralPublicKey").cdata(request.getEphemeralPublicKey()).up();
        builder.e("publicKeyHash").cdata(request.getPublicKeyHash()).up();
        builder.e("transactionId").cdata(request.getTransactionId()).up();
        builder.up();

        // Token data
        builder.e("signature").cdata(request.getSignature()).up();
        builder.e("version").cdata(request.getVersion()).up();
        builder.e("data").cdata(request.getData()).up();

        builder.up().up();
    }

}
