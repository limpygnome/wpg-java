package com.worldpay.sdk.wpg.xml.decorator;

import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class OrderDetailsDecorator
{
    public static void decorate(XmlBuildParams params, OrderDetails orderDetails)
    {
        UserPassAuth auth = (UserPassAuth) params.gatewayContext().getAuth();
        XmlBuilder builder = params.xmlBuilder();

        // build order element
        builder = builder
                .e("submit")
                .e("order")
                    .a("orderCode", orderDetails.getOrderCode());

        if (auth.getInstallationId() != null)
        {
            builder.a("installationId", String.valueOf(auth.getInstallationId()));
        }

        // append description
        builder = builder.e("description").cdata(orderDetails.getDescription()).up();

        // append amount
        Amount amount = orderDetails.getAmount();

        if (amount == null)
        {
            throw new IllegalArgumentException("Amount is mandatory for order details");
        }

        builder.e("amount")
                    .a("currencyCode", amount.getCurrency().ISO4217_CURRENCY_CODE)
                    .a("exponent", String.valueOf(amount.getExponent()))
                    .a("value", String.valueOf(amount.getValue()));
    }

}
