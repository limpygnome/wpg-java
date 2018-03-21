package com.worldpay.sdk.wpg.domain.payment;

/**
 * http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/reference/usefultables.htm#Acquirer
 *
 * TODO need to check this has everything, as docs do not list all
 */
public enum LastEvent
{
    AUTHORISED,
    REFERRED,
    REFUSED,
    FRAUD_SUSPICION,
    CANCELLED,
    CAPTURED,
    SENT_FOR_REFUND,

    // http://wp-support.crm.worldpay.com/app/answers/detail/a_id/894/~/what-do-the-different-payment-statuses-mean-and-when-will-you-send-me-a-status
    SENT_FOR_AUTHORISATION,
    ERROR,
    CHARGED_BACK,
    SETTLED,
    REFUNDED
}
