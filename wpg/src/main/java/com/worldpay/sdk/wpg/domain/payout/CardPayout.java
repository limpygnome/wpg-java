package com.worldpay.sdk.wpg.domain.payout;

import com.worldpay.sdk.wpg.domain.payment.Payment;

/**
 * The result from a card payout.
 */
public class CardPayout
{
    private final Payment payment;

    public CardPayout(Payment payment)
    {
        this.payment = payment;
    }

    /**
     * @return payment result; may not always be present
     */
    public Payment getPayment()
    {
        return payment;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardPayout payout = (CardPayout) o;

        return payment != null ? payment.equals(payout.payment) : payout.payment == null;
    }

    @Override
    public int hashCode()
    {
        return payment != null ? payment.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "CardPayout{" +
                "payment=" + payment +
                '}';
    }

}
