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

}
