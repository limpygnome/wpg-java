package com.worldpay.sdk.wpg.xml.serializer.payment;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;

public class PaymentMethodSerializer
{

    public static PaymentMethod convert(String paymentMethodMask)
    {
        if (paymentMethodMask == null)
        {
            throw new NullPointerException("Payment method mask is null");
        }

        PaymentMethod method = null;

        switch (paymentMethodMask)
        {
            case "VISA-SSL":
                method = PaymentMethod.VISA;
                break;
            case "ECMC-SSL":
                method = PaymentMethod.MASTERCARD;
                break;
            case "PAYPAL-SSL":
                method = PaymentMethod.PAYPAL;
                break;
        }

        if (method == null)
        {
            throw new IllegalStateException("Unknown payment method mask '" + paymentMethodMask + "'");
        }

        return method;
    }

}
