package com.worldpay.sdk.wpg.domain.notification;

import com.worldpay.sdk.wpg.domain.journal.Journal;
import com.worldpay.sdk.wpg.domain.payment.Payment;

import java.util.List;

public class OrderNotification
{
    private String orderCode;
    private final List<Payment> payments;
    private final Journal journal;

    public OrderNotification(String orderCode, List<Payment> payments, Journal journal)
    {
        this.orderCode = orderCode;
        this.payments = payments;
        this.journal = journal;
    }

    /**
     * @return The order code associated with this notification
     */
    public String getOrderCode()
    {
        return orderCode;
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

        if (orderCode != null ? !orderCode.equals(that.orderCode) : that.orderCode != null) return false;
        if (payments != null ? !payments.equals(that.payments) : that.payments != null) return false;
        return journal != null ? journal.equals(that.journal) : that.journal == null;
    }

    @Override
    public int hashCode()
    {
        int result = orderCode != null ? orderCode.hashCode() : 0;
        result = 31 * result + (payments != null ? payments.hashCode() : 0);
        result = 31 * result + (journal != null ? journal.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "OrderNotification{" +
                "orderCode='" + orderCode + '\'' +
                ", payments=" + payments +
                ", journal=" + journal +
                '}';
    }

}
