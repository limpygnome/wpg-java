package com.worldpay.sdk.wpg.xml;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PaymentMethodTranslator
{
    private static final Map<PaymentMethod, String> pmToMask;

    static
    {
        Map<PaymentMethod, String> result = new HashMap<>();
        result.put(PaymentMethod.VISA, "VISA-SSL");
        result.put(PaymentMethod.MASTERCARD, "ECMC-SSL");
        result.put(PaymentMethod.PAYPAL, "PAYPAL-EXPRESS");

        pmToMask = Collections.unmodifiableMap(result);
    }

    public static String getMask(PaymentMethod paymentMethod)
    {
        String mask = pmToMask.get(paymentMethod);
        if (mask == null)
        {
            throw new RuntimeException("Payment method '" + paymentMethod + "' missing from translator");
        }
        return mask;
    }

}
