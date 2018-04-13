package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.request.recurring.RecurringPaymentRequest;

public class PayAsOrderSerializer
{

    public static void decorate(XmlBuildParams params, RecurringPaymentRequest request)
    {
        XmlBuilder builder = params.xmlBuilder();

        OrderDetailsSerializer.decorateAndStartOrder(params, request.getOrderDetails());

        // decorate specific pay as order / recurring data
        String existingOrderCode = request.getExistingOrderCode();
        String existingMerchantCode = request.getExistingMerchantCode();

        builder.e("payAsOrder")
                .a("orderCode", existingOrderCode)
                .a("merchantCode", existingMerchantCode);

        String cvc = request.getCvc();

        if (cvc != null)
        {
            builder.a("cvc", cvc);
        }

        Amount existingAmount = request.getExistingAmount();
        AmountSerializer.write(builder, existingAmount);

        OrderDetailsSerializer.decorateFinishOrder(params);
    }

}
