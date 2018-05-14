package com.worldpay.sdk.wpg.domain.journal;

import java.time.LocalDate;
import java.util.List;

public class Journal
{
    private final LocalDate bookingDate;
    private final String type;
    private final String sent;
    private final List<JournalTransaction> transactions;

    // TODO not well understood, model may need improving
    private final List<JournalReference> references;
    private final List<JournalTypeDetail> typeDetails;

    public Journal(String type, String sent, LocalDate bookingDate, List<JournalTransaction> transactions, List<JournalReference> references, List<JournalTypeDetail> typeDetails)
    {
        this.type = type;
        this.sent = sent;
        this.bookingDate = bookingDate;
        this.transactions = transactions;
        this.references = references;
        this.typeDetails = typeDetails;
    }

    public String getType()
    {
        return type;
    }

    public String getSent()
    {
        return sent;
    }

    public LocalDate getBookingDate()
    {
        return bookingDate;
    }

    public List<JournalTransaction> getTransactions()
    {
        return transactions;
    }

    public List<JournalReference> getReferences()
    {
        return references;
    }

    public List<JournalTypeDetail> getTypeDetails()
    {
        return typeDetails;
    }

    @Override
    public String toString()
    {
        return "Journal{" +
                "bookingDate=" + bookingDate +
                ", type='" + type + '\'' +
                ", sent='" + sent + '\'' +
                ", transactions=" + transactions +
                ", references=" + references +
                ", typeDetails=" + typeDetails +
                '}';
    }

}
