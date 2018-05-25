package com.worldpay.sdk.wpg.domain.journal;

import com.worldpay.sdk.wpg.domain.payment.Amount;

public class JournalTransaction
{
    private final String batchId;
    private final JournalTransactionType type;
    private final Amount amount;

    public JournalTransaction(String batchId, JournalTransactionType type, Amount amount)
    {
        this.batchId = batchId;
        this.type = type;
        this.amount = amount;
    }

    public String getBatchId()
    {
        return batchId;
    }

    public JournalTransactionType getType()
    {
        return type;
    }

    public Amount getAmount()
    {
        return amount;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalTransaction that = (JournalTransaction) o;

        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;
        if (type != that.type) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode()
    {
        int result = batchId != null ? batchId.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "JournalTransaction{" +
                "batchId='" + batchId + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
