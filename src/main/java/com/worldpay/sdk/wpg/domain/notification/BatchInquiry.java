package com.worldpay.sdk.wpg.domain.notification;

import java.util.Collections;
import java.util.List;

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

    public BatchStatus getStatus()
    {
        return status;
    }

    public int getTransactions()
    {
        return transactions;
    }

    public List<BatchError> getErrors()
    {
        return errors;
    }
}
