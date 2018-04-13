package com.worldpay.sdk.wpg.domain.payment;

import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsRequired;
import com.worldpay.sdk.wpg.exception.WpgRequestException;

import java.io.Serializable;

public class PaymentResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Payment payment;
    private ThreeDsRequired threeDsRequired;

    private PaymentStatus status;

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
