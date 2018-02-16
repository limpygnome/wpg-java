package com.worldpay.sdk.wpg.xml.decorator;

import com.jamesmurty.utils.XMLBuilder2;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;

public class OrderDetailsDecorator
{
    public static void decorate(XmlBuildParams params, OrderDetails orderDetails)
    {
        UserPassAuth auth = (UserPassAuth) params.gatewayContext().getAuth();
        XMLBuilder2 builder = params.xmlBuilder2();

        // build order element
        builder = builder
                .e("submit")
                .e("order")
                    .attr("orderCode", orderDetails.getOrderCode());

        if (auth.getInstallationId() != null)
        {
            builder.attr("installationId", String.valueOf(auth.getInstallationId()));
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
                    .attr("currencyCode", amount.getCurrency().ISO4217_CURRENCY_CODE)
                    .attr("exponent", String.valueOf(amount.getExponent()))
                    .attr("value", String.valueOf(amount.getValue()));
    }

}
