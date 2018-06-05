package com.worldpay.sdk.wpg.internal.xml.serializer.payment;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
import com.worldpay.sdk.wpg.internal.xml.PaymentMethodTranslator;

public class PaymentMethodSerializer
{

    public static PaymentMethod convert(String paymentMethodMask)
    {
        if (paymentMethodMask == null)
        {
            throw new NullPointerException("Payment method mask is null");
        }

        // Attempt to find payment method
        PaymentMethod paymentMethod = PaymentMethodTranslator.getPaymentMethod(paymentMethodMask);

        // Wipe out child brand if not found (cards only)
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
            paymentMethod = PaymentMethodTranslator.getPaymentMethod(parentBrandMask);
        }

        return paymentMethod;
    }

}
