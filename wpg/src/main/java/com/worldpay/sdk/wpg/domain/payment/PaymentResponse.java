package com.worldpay.sdk.wpg.domain.payment;

import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsRequired;
import com.worldpay.sdk.wpg.exception.WpgRequestException;

import java.io.Serializable;

/**
 * Represents the response from submitting payment details.
 *
 * You can use {@link #getStatus()} to check whether the payment is in-flight, or requires 3ds / threeds
 * authentication. Refer to {@link ThreeDsRequired} for details.
 */
public class PaymentResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    private PaymentStatus status;

    private Payment payment;
    private ThreeDsRequired threeDsRequired;

    public PaymentResponse(Payment payment) throws WpgRequestException
    {
        this.payment = payment;
        this.status = PaymentStatus.PAYMENT_RESULT;
    }

    public PaymentResponse(ThreeDsRequired threeDsRequired)
    {
        this.threeDsRequired = threeDsRequired;
        this.status = PaymentStatus.THREEDS_REQUESTED;
    }

    public PaymentStatus getStatus()
    {
        return status;
    }

    public Payment getPayment()
    {
        return payment;
    }

    public ThreeDsRequired getThreeDsRequired()
    {
        return threeDsRequired;
    }

}
