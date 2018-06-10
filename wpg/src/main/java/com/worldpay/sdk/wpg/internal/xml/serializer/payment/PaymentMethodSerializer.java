package com.worldpay.sdk.wpg.internal.xml.serializer.payment;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethodType;
import com.worldpay.sdk.wpg.internal.xml.PaymentMethodTypeTranslator;

public class PaymentMethodSerializer
{

    public static PaymentMethodType convert(String paymentMethodMask)
    {
        if (paymentMethodMask == null)
        {
            throw new NullPointerException("Payment method mask is null");
        }

        // Attempt to find payment method
        PaymentMethodType paymentMethodType = PaymentMethodTypeTranslator.getType(paymentMethodMask);

        // Wipe out child brand if not found (cards only)
        if (paymentMethodType != null)
        {
            if (paymentMethodMask.startsWith("VISA_") ||
                    paymentMethodMask.startsWith("ECMC_") ||
                    paymentMethodMask.startsWith("AMEX_") ||
                    paymentMethodMask.startsWith("DINERS_") ||
                    paymentMethodMask.startsWith("CB_") ||
                    paymentMethodMask.startsWith("CARTEBLEUE_") ||
                    paymentMethodMask.startsWith("DANKORT_") ||
                    paymentMethodMask.startsWith("DISCOVER_") ||
                    paymentMethodMask.startsWith("JCB_") ||
                    paymentMethodMask.startsWith("TROY_"))
            {
                String parentBrandMask = paymentMethodMask.substring(0, paymentMethodMask.indexOf('_')) + "-SSL";
                paymentMethodType = PaymentMethodTypeTranslator.getType(parentBrandMask);
            }
        }

        return paymentMethodType;
    }

}
