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
    public String toString()
    {
        return "JournalTransaction{" +
                "batchId='" + batchId + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
