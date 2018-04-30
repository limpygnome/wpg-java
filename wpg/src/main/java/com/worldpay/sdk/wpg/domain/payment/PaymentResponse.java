package com.worldpay.sdk.wpg.domain.payment;

import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsDetails;
import com.worldpay.sdk.wpg.exception.WpgRequestException;

import java.io.Serializable;

/**
 * Represents the response from submitting payment details.
 *
 * You can use {@link #getStatus()} to check whether the payment is in-flight, or requires 3ds / threeds
 * authentication. Refer to {@link ThreeDsDetails} for details.
 */
public class PaymentResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    private PaymentStatus status;

    private Payment payment;
    private ThreeDsDetails threeDsDetails;

    public PaymentResponse(Payment payment) throws WpgRequestException
    {
        this.payment = payment;
        this.status = PaymentStatus.PAYMENT_RESULT;
    }

    public PaymentResponse(ThreeDsDetails threeDsDetails)
    {
        this.threeDsDetails = threeDsDetails;
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

    public ThreeDsDetails getThreeDsDetails()
    {
        return threeDsDetails;
    }

}
