package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.journal.Journal;
import com.worldpay.sdk.wpg.domain.journal.JournalReference;
import com.worldpay.sdk.wpg.domain.journal.JournalTransaction;
import com.worldpay.sdk.wpg.domain.journal.JournalTransactionType;
import com.worldpay.sdk.wpg.domain.journal.JournalTypeDetail;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JournalSerializer
{

    public static Journal read(XmlBuilder builder) throws WpgRequestException
    {
        builder.e("journal");

        // Read booking date
        LocalDate bookingDate = readBookingDate(builder);

        // Read accountTxs
        List<JournalTransaction> transactions = readJournalTransactions(builder);

        // Read journal references
        List<JournalReference> references = readJournalReferences(builder);

        // Read journal type details
        List<JournalTypeDetail> typeDetails = readJournalDetails(builder);

        // Read attribs
        String type = builder.a("journalType");
        String sent = builder.a("sent");

        // Reset
        builder.up();

        // Give back result
        Journal journal = new Journal(type, sent, bookingDate, transactions, references, typeDetails);
        return journal;
    }

    private static LocalDate readBookingDate(XmlBuilder builder) throws WpgRequestException
    {
        builder.e("bookingDate").e("date");
        LocalDate result = DateSerializer.readDate(builder);
        builder.up().up();
        return result;
    }

    private static List<JournalTransaction> readJournalTransactions(XmlBuilder builder) throws WpgRequestException
    {
        List<XmlBuilder> children = builder.childTags("accountTx");
        List<JournalTransaction> transactions = new ArrayList<>(children.size());
        for (XmlBuilder childBuilder : children)
        {
            JournalTransaction transaction = readJournalTransaction(childBuilder);
            transactions.add(transaction);
        }
        return transactions;
    }

    private static JournalTransaction readJournalTransaction(XmlBuilder builder) throws WpgRequestException
    {
        String batchId = builder.a("batchId");
        JournalTransactionType type = readJournalTransactionType(builder);
        Amount amount = readJournalTransactionAmount(builder);

        JournalTransaction transaction = new JournalTransaction(batchId, type, amount);
        return transaction;
    }

    private static JournalTransactionType readJournalTransactionType(XmlBuilder builder)
    {
        String rawType = builder.a("accountType");
        JournalTransactionType type = JournalTransactionType.valueOf(rawType);
        return type;
    }

    private static Amount readJournalTransactionAmount(XmlBuilder builder) throws WpgRequestException
    {
        builder.e("amount");
        Amount amount = AmountSerializer.read(builder);
        builder.up();
        return amount;
    }

    private static List<JournalReference> readJournalReferences(XmlBuilder builder)
    {
        List<XmlBuilder> children = builder.childTags("journalReference");
        List<JournalReference> references = new ArrayList<>(children.size());
        for (XmlBuilder childBuilder : children)
        {
            JournalReference reference = readJournalReference(childBuilder);
            references.add(reference);
        }
        return references;
    }

    private static JournalReference readJournalReference(XmlBuilder builder)
    {
        String type = builder.a("type");
        String reference = builder.a("reference");

        JournalReference result = new JournalReference(type, reference);
        return result;
    }

    private static List<JournalTypeDetail> readJournalDetails(XmlBuilder builder)
    {
        List<XmlBuilder> children = builder.childTags("journalTypeDetail");
        List<JournalTypeDetail> details = new ArrayList<>(children.size());
        for (XmlBuilder childBuilder : children)
        {
            JournalTypeDetail detail = readJournalTypeDetail(childBuilder);
            details.add(detail);
        }
        return details;
    }

    private static JournalTypeDetail readJournalTypeDetail(XmlBuilder builder)
    {
        String id = builder.getCdata("JournalTypeDetailId");
        String description = builder.getCdata("Description");

        List<XmlBuilder> children = builder.childTags("ReversalReason");
        List<String> reversalReasons = new ArrayList<>(children.size());
        for (XmlBuilder childBuilder : children)
        {
            String reversalReason = childBuilder.cdata();
            reversalReasons.add(reversalReason);
        }

        JournalTypeDetail detail = new JournalTypeDetail(id, description, reversalReasons);
        return detail;
    }

}
