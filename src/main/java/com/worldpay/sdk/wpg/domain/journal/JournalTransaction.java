package com.worldpay.sdk.wpg.domain.journal;

import com.worldpay.sdk.wpg.domain.payment.Amount;

public class JournalTransaction
{
    private String batchId;
    private JournalTransactionType accountType;
    private Amount amount;
}
