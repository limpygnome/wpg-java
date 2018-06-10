package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethodType;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodTypeFilter;
import com.worldpay.sdk.wpg.internal.xml.PaymentMethodTypeTranslator;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

import java.util.List;

public class PaymentMethodTypeMaskSerializer
{
    public static void decorate(XmlBuildParams params, PaymentMethodTypeFilter paymentMethodTypeFilter)
    {
        XmlBuilder builder = params.xmlBuilder();

        // fetch lists; filter can be null
        List<PaymentMethodType> included;
        List<PaymentMethodType> excluded;

        if (paymentMethodTypeFilter != null)
        {
            included = paymentMethodTypeFilter.getIncluded();
            excluded = paymentMethodTypeFilter.getExcluded();
        }
        else
        {
            included = null;
            excluded = null;
        }

        // Move to correct element
        builder.e("paymentMethodMask");

        if (paymentMethodTypeFilter == null || (included.isEmpty() && excluded.isEmpty()))
        {
            builder.e("include")
                    .a("code","ALL")
                    .up();
        }
        else
        {
            // Validate included and excluded not both specified
            if (!included.isEmpty() && !excluded.isEmpty())
            {
                throw new IllegalArgumentException("Only either payment methods included or excluded can be defined, not both");
            }

            if (!included.isEmpty())
            {
                appendList(builder, "include", included);
            }
            else if (!excluded.isEmpty())
            {
                appendList(builder, "exclude", excluded);
            }
        }

        // reset back
        builder.up();
    }

    private static void appendList(XmlBuilder builder, String elementName, List<PaymentMethodType> list)
    {
        for (PaymentMethodType paymentMethodType : list)
        {
            String mask = PaymentMethodTypeTranslator.getMask(paymentMethodType);
            builder.e(elementName, true).a("code", mask)
                    .up();
        }
    }


}
