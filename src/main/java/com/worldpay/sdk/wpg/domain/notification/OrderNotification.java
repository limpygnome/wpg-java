package com.worldpay.sdk.wpg.domain.notification;

import com.worldpay.sdk.wpg.domain.journal.Journal;
import com.worldpay.sdk.wpg.domain.payment.Payment;

import java.util.List;

public class OrderNotification
{
    private final List<Payment> payments;
    private final Journal journal;

    public OrderNotification(List<Payment> payments, Journal journal)
    {
        this.payments = payments;
        this.journal = journal;
    }

    public List<Payment> getPayments()
    {
        return payments;
    }

    public Journal getJournal()
    {
        return journal;
    }

    @Override
    public String toString()
    {
        return "OrderNotification{" +
                "payments=" + payments +
                ", journal=" + journal +
                '}';
    }
}
