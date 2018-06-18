package com.worldpay.sdk.wpg.domain.payment;

import com.worldpay.sdk.wpg.internal.xml.serializer.payment.PaymentMethodTypeSerializer;

/**
 * The payment method mask.
 *
 * Note: when adding new payment methods, ensure {@link PaymentMethodTypeSerializer}
 * is updated.
 */
public enum PaymentMethodType
{
    CARD,
    VISA,
    MASTERCARD,
    AMEX,
    DINERS,
    CARTE_BLEUE,
    DANKORT,
    DISCOVER,
    JCB,
    AIRPLUS,
    UATP,
    LASER,
    TROY,

    PAYPAL,
    ASTROPAY,
    BOLETO,
    SEPA_DIRECT_DEBIT,
    ACH,
    MASTERPASS,
    IDEAL,
    GIROPAY,
    ALIPAY,
    CASHU,
    DINEROMAIL_7ELEVEN,
    DINEROMAIL_OXXO,
    DINEROMAIL_ONLINE_BT,
    DINEROMAIL_SERVIPAG,
    EUTELLER,
    MISTERCASH,
    PAYU,
    POLI,
    POLINZ,
    POSTEPAY,
    PRZELEWY,
    QIWI,
    SAFETYPAY,
    SKRILL,
    SOFORT,
    SOFORT_CH,
    TRUSTPAY_CZ,
    TRUSTPAY_EE,
    TRUSTPAY_SK,
    WEBMONEY,
    YANDEX_MONEY,
    KLARNA
}
