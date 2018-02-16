package com.worldpay.sdk.wpg.domain.payment;

/**
 * http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/reference/usefultables.htm#Acquirer
 */
public enum PaymentStatus
{
    AUTHORISED,
    REFERRED,
    REFUSED,
    FRAUD_SUSPICION
}
