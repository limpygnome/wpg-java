package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class PaymentMethodTranslator
{
    private static final Map<PaymentMethod, String> pmToMask;
    private static final Map<String, PaymentMethod> maskToPm;

    static
    {
        Map<PaymentMethod, String> result = new HashMap<>();
        result.put(PaymentMethod.CARD, "CARD-SSL");
        result.put(PaymentMethod.VISA, "VISA-SSL");
        result.put(PaymentMethod.MASTERCARD, "ECMC-SSL");
        result.put(PaymentMethod.AMEX, "AMEX-SSL");
        result.put(PaymentMethod.DINERS, "DINERS-SSL");
        result.put(PaymentMethod.CARTE_BLEUE, "CB-SSL");
        result.put(PaymentMethod.DANKORT, "DANKORT-SSL");
        result.put(PaymentMethod.DISCOVER, "DISCOVER-SSL");
        result.put(PaymentMethod.JCB, "JCB-SSL");
        result.put(PaymentMethod.AIRPLUS, "AIRPLUS-SSL");
        result.put(PaymentMethod.UATP, "UATP-SSL");
        result.put(PaymentMethod.LASER, "LASER-SSL");
        result.put(PaymentMethod.TROY, "TROY-SSL");

        result.put(PaymentMethod.PAYPAL, "PAYPAL-EXPRESS");
        result.put(PaymentMethod.ASTROPAY, "ASTROPAY-SSL");
        result.put(PaymentMethod.BOLETO, "BOLETO-SSL");
        result.put(PaymentMethod.SEPA_DIRECT_DEBIT, "SEPA_DIRECT_DEBIT-SSL");
        result.put(PaymentMethod.ACH, "ACH-SSL");
        result.put(PaymentMethod.MASTERPASS, "MASTERPASS-SSL");
        result.put(PaymentMethod.IDEAL, "IDEAL-SSL");
        result.put(PaymentMethod.GIROPAY, "GIROPAY-SSL");
        result.put(PaymentMethod.ALIPAY, "ALIPAY-SSL");
        result.put(PaymentMethod.CASHU, "CASHU-SSL");
        result.put(PaymentMethod.DINEROMAIL_7ELEVEN, "DINEROMAIL_7ELEVEN-SSL");
        result.put(PaymentMethod.DINEROMAIL_OXXO, "DINEROMAIL_OXXO-SSL");
        result.put(PaymentMethod.DINEROMAIL_ONLINE_BT, "DINEROMAIL_ONLINE_BT-SSL");
        result.put(PaymentMethod.DINEROMAIL_SERVIPAG, "DINEROMAIL_SERVIPAG-SSL");

        result.put(PaymentMethod.EUTELLER, "EUTELLER-SSL");
        result.put(PaymentMethod.MISTERCASH, "MISTERCASH-SSL");
        result.put(PaymentMethod.PAYU, "PAYU-SSL");
        result.put(PaymentMethod.POLI, "POLI-SSL");
        result.put(PaymentMethod.POLINZ, "POLINZ-SSL");
        result.put(PaymentMethod.POSTEPAY, "POSTEPAY-SSL");
        result.put(PaymentMethod.PRZELEWY, "PRZELEWY-SSL");
        result.put(PaymentMethod.QIWI, "QIWI-SSL");
        result.put(PaymentMethod.SAFETYPAY, "SAFETYPAY-SSL");
        result.put(PaymentMethod.SKRILL, "SKRILL-SSL");
        result.put(PaymentMethod.SOFORT, "SOFORT-SSL");
        result.put(PaymentMethod.SOFORT_CH, "SOFORT_CH-SSL");
        result.put(PaymentMethod.TRUSTPAY_CZ, "TRUSTPAY_CZ-SSL");
        result.put(PaymentMethod.TRUSTPAY_EE, "TRUSTPAY_EE-SSL");
        result.put(PaymentMethod.TRUSTPAY_SK, "TRUSTPAY_SK-SSL");
        result.put(PaymentMethod.WEBMONEY, "WEBMONEY-SSL");
        result.put(PaymentMethod.YANDEX_MONEY, "YANDEX_MONEY-SSL");
        result.put(PaymentMethod.KLARNA, "KLARNA-SSL");

        pmToMask = Collections.unmodifiableMap(result);

        // Flip it
        maskToPm = Collections.unmodifiableMap(
                result.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey))
        );
    }

    /**
     * @param paymentMethod The {@link PaymentMethod}
     * @return The payment method mask
     */
    public static String getMask(PaymentMethod paymentMethod)
    {
        String mask = pmToMask.get(paymentMethod);
        return mask;
    }

    /**
     * @param paymentMethodMask The payment method mask
     * @return The {@link PaymentMethod}
     */
    public static PaymentMethod getPaymentMethod(String paymentMethodMask)
    {
        PaymentMethod paymentMethod = maskToPm.get(paymentMethodMask);
        return paymentMethod;
    }

}
