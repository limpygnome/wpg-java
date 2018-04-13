package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class OrderDetailsSerializer
{

    public static void decorateMerchantCode(XmlBuildParams params)
    {
        UserPassAuth auth = (UserPassAuth) params.gatewayContext().getAuth();

        XmlBuilder builder = params.xmlBuilder();
        builder.a("merchantCode", auth.getMerchantCode());
    }

    public static void decorateAndStartOrder(XmlBuildParams params, OrderDetails orderDetails)
    {
        UserPassAuth auth = (UserPassAuth) params.gatewayContext().getAuth();
        XmlBuilder builder = params.xmlBuilder();

        // add merchant code (handled differently for batch)
        if (!params.isBatch())
        {
            decorateMerchantCode(params);
            builder.e("submit");
        }

        // start order element
        builder.e("order", true).a("orderCode", orderDetails.getOrderCode());

        if (auth.getInstallationId() != null)
        {
            builder.a("installationId", auth.getInstallationId());
        }

        // append description
        builder = builder.e("description").cdata(orderDetails.getDescription()).up();

        // append amount
        Amount amount = orderDetails.getAmount();

        if (amount == null)
        {
            throw new IllegalArgumentException("Amount is mandatory for order details");
        }

        AmountSerializer.write(builder, amount);
    }

    public static void decorateFinishOrder(XmlBuildParams params)
    {
        XmlBuilder builder = params.xmlBuilder();

        if (params.isBatch())
        {
             builder.up();
        }
        else
        {
            builder.reset();
        }
    }

}
