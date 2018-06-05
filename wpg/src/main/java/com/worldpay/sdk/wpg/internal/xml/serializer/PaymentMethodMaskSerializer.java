package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodFilter;
import com.worldpay.sdk.wpg.internal.xml.PaymentMethodTranslator;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

import java.util.List;

public class PaymentMethodMaskSerializer
{
    public static void decorate(XmlBuildParams params, PaymentMethodFilter paymentMethodFilter)
    {
        XmlBuilder builder = params.xmlBuilder();

        // fetch lists; filter can be null
        List<PaymentMethod> included;
        List<PaymentMethod> excluded;

        if (paymentMethodFilter != null)
        {
            included = paymentMethodFilter.getIncluded();
            excluded = paymentMethodFilter.getExcluded();
        }
        else
        {
            included = null;
            excluded = null;
        }

        // Move to correct element
        builder.e("paymentMethodMask");

        if (paymentMethodFilter == null || (included.isEmpty() && excluded.isEmpty()))
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

    private static void appendList(XmlBuilder builder, String elementName, List<PaymentMethod> list)
    {
        for (PaymentMethod paymentMethod : list)
        {
            String mask = PaymentMethodTranslator.getMask(paymentMethod);
            builder.e(elementName, true).a("code", mask)
                    .up();
        }
    }


}
