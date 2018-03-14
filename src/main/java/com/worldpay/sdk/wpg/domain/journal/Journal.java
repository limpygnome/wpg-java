package com.worldpay.sdk.wpg.domain.journal;

import java.time.LocalDate;
import java.util.List;

public class Journal
{
    private LocalDate bookingDate;
    private List<JournalTransaction> transactions;

    // TODO understand these more, hard to model
    private List<JournalReference> references;
    private List<JournalTypeDetail> typeDetails;
}
