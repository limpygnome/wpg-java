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

    /**
     * @return The parsed payment(s) present in the order notification
     */
    public List<Payment> getPayments()
    {
        return payments;
    }

    /**
     * @return The journal changes
     */
    public Journal getJournal()
    {
        return journal;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderNotification that = (OrderNotification) o;

        if (payments != null ? !payments.equals(that.payments) : that.payments != null) return false;
        return journal != null ? journal.equals(that.journal) : that.journal == null;
    }

    @Override
    public int hashCode()
    {
        int result = payments != null ? payments.hashCode() : 0;
        result = 31 * result + (journal != null ? journal.hashCode() : 0);
        return result;
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
