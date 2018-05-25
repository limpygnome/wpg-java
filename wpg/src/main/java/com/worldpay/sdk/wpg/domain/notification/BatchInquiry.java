package com.worldpay.sdk.wpg.domain.notification;

import java.util.Collections;
import java.util.List;

/**
 * The status and details for a batch of modifications.
 */
public class BatchInquiry
{
    private final BatchStatus status;
    private final int transactions;
    private final List<BatchError> errors;

    public BatchInquiry(BatchStatus status, int transactions, List<BatchError> errors)
    {
        this.status = status;
        this.transactions = transactions;
        this.errors = Collections.unmodifiableList(errors);
    }

    /**
     * @return The current status
     */
    public BatchStatus getStatus()
    {
        return status;
    }

    /**
     * @return Number of transactions
     */
    public int getTransactions()
    {
        return transactions;
    }

    /**
     * @return Any errors from processing the batch
     */
    public List<BatchError> getErrors()
    {
        return errors;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchInquiry inquiry = (BatchInquiry) o;

        if (transactions != inquiry.transactions) return false;
        if (status != inquiry.status) return false;
        return errors != null ? errors.equals(inquiry.errors) : inquiry.errors == null;
    }

    @Override
    public int hashCode()
    {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + transactions;
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "BatchInquiry{" +
                "status=" + status +
                ", transactions=" + transactions +
                ", errors=" + errors +
                '}';
    }

}
