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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journal journal = (Journal) o;

        if (bookingDate != null ? !bookingDate.equals(journal.bookingDate) : journal.bookingDate != null) return false;
        if (type != null ? !type.equals(journal.type) : journal.type != null) return false;
        if (sent != null ? !sent.equals(journal.sent) : journal.sent != null) return false;
        if (transactions != null ? !transactions.equals(journal.transactions) : journal.transactions != null)
            return false;
        if (references != null ? !references.equals(journal.references) : journal.references != null) return false;
        return typeDetails != null ? typeDetails.equals(journal.typeDetails) : journal.typeDetails == null;
    }

    @Override
    public int hashCode()
    {
        int result = bookingDate != null ? bookingDate.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (sent != null ? sent.hashCode() : 0);
        result = 31 * result + (transactions != null ? transactions.hashCode() : 0);
        result = 31 * result + (references != null ? references.hashCode() : 0);
        result = 31 * result + (typeDetails != null ? typeDetails.hashCode() : 0);
        return result;
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
