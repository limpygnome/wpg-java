package com.worldpay.sdk.wpg.xml.decorator;

import com.jamesmurty.utils.XMLBuilder2;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodFilter;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;

import java.util.List;

public class PaymentMethodMaskDecorator
{
    public static void decorate(XmlBuildParams params, PaymentMethodFilter paymentMethodFilter)
    {
        XMLBuilder2 builder = params.xmlBuilder2();

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
        builder.e("submit")
                .e("order")
                .e("paymentMethodMask");

        if (paymentMethodFilter == null || (included.isEmpty() && excluded.isEmpty()))
        {
            builder.e("include")
                    .attr("code","ALL")
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

        // reset
        builder.up(3);
    }

    private static void appendList(XMLBuilder2 builder, String elementName, List<PaymentMethod> list)
    {
        for (PaymentMethod paymentMethod : list)
        {
            String mask = getLegacyPaymentMethodMask(paymentMethod);
            builder.e(elementName).attr("code", mask).up();
        }
    }

    private static String getLegacyPaymentMethodMask(PaymentMethod paymentMethod)
    {
        switch (paymentMethod)
        {
            case VISA:
                return "VISA-SSL";
            case MASTERCARD:
                return "ECMC-SSL";
            case PAYPAL:
                return "PAYPAL-EXPRESS";
            default:
                throw new IllegalArgumentException("Payment method " + paymentMethod.name() + " not supported");
        }
    }


}
