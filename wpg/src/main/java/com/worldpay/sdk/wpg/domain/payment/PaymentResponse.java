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

    /**
     * Use this to determine the outcome of submitting payment details. The traditional flow may be that the shopper
     * is required to carry out further card authentication (3ds) before the payment can be authorised.
     *
     * @return Status of the payment
     */
    public PaymentStatus getStatus()
    {
        return status;
    }

    /**
     * This is only present when status is {@link PaymentStatus#PAYMENT_RESULT}.
     *
     * @return The payment
     */
    public Payment getPayment()
    {
        return payment;
    }

    /**
     * This is only present when status is {@link PaymentStatus#THREEDS_REQUESTED}.
     *
     * @return ThreeDS card authentication details
     */
    public ThreeDsDetails getThreeDsDetails()
    {
        return threeDsDetails;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentResponse that = (PaymentResponse) o;

        if (status != that.status) return false;
        if (payment != null ? !payment.equals(that.payment) : that.payment != null) return false;
        return threeDsDetails != null ? threeDsDetails.equals(that.threeDsDetails) : that.threeDsDetails == null;
    }

    @Override
    public int hashCode()
    {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        result = 31 * result + (threeDsDetails != null ? threeDsDetails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "PaymentResponse{" +
                "status=" + status +
                ", payment=" + payment +
                ", threeDsDetails=" + threeDsDetails +
                '}';
    }

}
