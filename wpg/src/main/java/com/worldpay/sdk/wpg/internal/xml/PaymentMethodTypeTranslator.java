package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethodType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class PaymentMethodTypeTranslator
{
    private static final Map<PaymentMethodType, String> pmToMask;
    private static final Map<String, PaymentMethodType> maskToPm;

    static
    {
        Map<PaymentMethodType, String> result = new HashMap<>();
        result.put(PaymentMethodType.CARD, "CARD-SSL");
        result.put(PaymentMethodType.VISA, "VISA-SSL");
        result.put(PaymentMethodType.MASTERCARD, "ECMC-SSL");
        result.put(PaymentMethodType.AMEX, "AMEX-SSL");
        result.put(PaymentMethodType.DINERS, "DINERS-SSL");
        result.put(PaymentMethodType.CARTE_BLEUE, "CB-SSL");
        result.put(PaymentMethodType.DANKORT, "DANKORT-SSL");
        result.put(PaymentMethodType.DISCOVER, "DISCOVER-SSL");
        result.put(PaymentMethodType.JCB, "JCB-SSL");
        result.put(PaymentMethodType.AIRPLUS, "AIRPLUS-SSL");
        result.put(PaymentMethodType.UATP, "UATP-SSL");
        result.put(PaymentMethodType.LASER, "LASER-SSL");
        result.put(PaymentMethodType.TROY, "TROY-SSL");

        result.put(PaymentMethodType.PAYPAL, "PAYPAL-EXPRESS");
        result.put(PaymentMethodType.ASTROPAY, "ASTROPAY-SSL");
        result.put(PaymentMethodType.BOLETO, "BOLETO-SSL");
        result.put(PaymentMethodType.SEPA_DIRECT_DEBIT, "SEPA_DIRECT_DEBIT-SSL");
        result.put(PaymentMethodType.ACH, "ACH-SSL");
        result.put(PaymentMethodType.MASTERPASS, "MASTERPASS-SSL");
        result.put(PaymentMethodType.IDEAL, "IDEAL-SSL");
        result.put(PaymentMethodType.GIROPAY, "GIROPAY-SSL");
        result.put(PaymentMethodType.ALIPAY, "ALIPAY-SSL");
        result.put(PaymentMethodType.CASHU, "CASHU-SSL");
        result.put(PaymentMethodType.DINEROMAIL_7ELEVEN, "DINEROMAIL_7ELEVEN-SSL");
        result.put(PaymentMethodType.DINEROMAIL_OXXO, "DINEROMAIL_OXXO-SSL");
        result.put(PaymentMethodType.DINEROMAIL_ONLINE_BT, "DINEROMAIL_ONLINE_BT-SSL");
        result.put(PaymentMethodType.DINEROMAIL_SERVIPAG, "DINEROMAIL_SERVIPAG-SSL");

        result.put(PaymentMethodType.EUTELLER, "EUTELLER-SSL");
        result.put(PaymentMethodType.MISTERCASH, "MISTERCASH-SSL");
        result.put(PaymentMethodType.PAYU, "PAYU-SSL");
        result.put(PaymentMethodType.POLI, "POLI-SSL");
        result.put(PaymentMethodType.POLINZ, "POLINZ-SSL");
        result.put(PaymentMethodType.POSTEPAY, "POSTEPAY-SSL");
        result.put(PaymentMethodType.PRZELEWY, "PRZELEWY-SSL");
        result.put(PaymentMethodType.QIWI, "QIWI-SSL");
        result.put(PaymentMethodType.SAFETYPAY, "SAFETYPAY-SSL");
        result.put(PaymentMethodType.SKRILL, "SKRILL-SSL");
        result.put(PaymentMethodType.SOFORT, "SOFORT-SSL");
        result.put(PaymentMethodType.SOFORT_CH, "SOFORT_CH-SSL");
        result.put(PaymentMethodType.TRUSTPAY_CZ, "TRUSTPAY_CZ-SSL");
        result.put(PaymentMethodType.TRUSTPAY_EE, "TRUSTPAY_EE-SSL");
        result.put(PaymentMethodType.TRUSTPAY_SK, "TRUSTPAY_SK-SSL");
        result.put(PaymentMethodType.WEBMONEY, "WEBMONEY-SSL");
        result.put(PaymentMethodType.YANDEX_MONEY, "YANDEX_MONEY-SSL");
        result.put(PaymentMethodType.KLARNA, "KLARNA-SSL");

        pmToMask = Collections.unmodifiableMap(result);

        // Flip it
        maskToPm = Collections.unmodifiableMap(
                result.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey))
        );
    }

    /**
     * @param paymentMethodType The {@link PaymentMethodType}
     * @return The payment method mask
     */
    public static String getMask(PaymentMethodType paymentMethodType)
    {
        String mask = pmToMask.get(paymentMethodType);
        return mask;
    }

    /**
     * @param paymentMethodMask The payment method mask
     * @return The {@link PaymentMethodType}
     */
    public static PaymentMethodType getType(String paymentMethodMask)
    {
        PaymentMethodType paymentMethodType = maskToPm.get(paymentMethodMask);
        return paymentMethodType;
    }

}
