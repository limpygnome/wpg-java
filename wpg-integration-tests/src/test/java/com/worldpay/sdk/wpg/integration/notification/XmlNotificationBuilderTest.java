package com.worldpay.sdk.wpg.integration.notification;

import com.worldpay.sdk.wpg.builder.XmlNotificationBuilder;
import com.worldpay.sdk.wpg.domain.journal.Journal;
import com.worldpay.sdk.wpg.domain.journal.JournalReference;
import com.worldpay.sdk.wpg.domain.journal.JournalTransaction;
import com.worldpay.sdk.wpg.domain.journal.JournalTransactionType;
import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.domain.payment.DebitCreditIndicator;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodType;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import org.junit.Test;

import java.io.InputStream;
import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * This tests {@link XmlNotificationBuilder}, as well as dependencies for parsing XML.
 *
 * Thus this integration test spreads across multiple areas, outside of notifications - useful!
 */
public class XmlNotificationBuilderTest
{

    @Test(expected = WpgMalformedException.class)
    public void malformedThrows() throws WpgMalformedException
    {
        XmlNotificationBuilder builder = new XmlNotificationBuilder();
        builder.read("qwertyuiop");
    }

    @Test
    public void authorised_asExpected() throws WpgMalformedException
    {
        // Given
        InputStream inputStream = XmlNotificationBuilderTest.class.getResourceAsStream("/order-notification/authorised.txt");

        // When
        XmlNotificationBuilder builder = new XmlNotificationBuilder();
        OrderNotification orderNotification = builder.read(inputStream);

        // Then
        assertNotNull(orderNotification);
        assertThat(orderNotification.getOrderCode(), is("Your_order_code"));
        assertThat(orderNotification.getPayments().size(), is(1));

        // -- Journal
        Journal journal = orderNotification.getJournal();
        assertThat(journal.getBookingDate(), is(LocalDate.of(2020, 01, 01)));

        JournalTransaction tx = journal.getTransactions().get(0);
        assertThat(tx.getBatchId(), is("30"));
        assertThat(tx.getType(), is(JournalTransactionType.IN_PROCESS_AUTHORISED));
        assertThat(tx.getAmount().getCurrencyCode(), is("EUR"));
        assertThat(tx.getAmount().getExponent(), is(2L));
        assertThat(tx.getAmount().getValue(), is(2400L));
        assertThat(tx.getAmount().getDebitCreditIndicator(), is(DebitCreditIndicator.CREDIT));

        // -- Payment
        Payment payment = orderNotification.getPayments().get(0);
        assertThat(payment.getPaymentMethodType(), is(PaymentMethodType.VISA));
        assertThat(payment.getAmount().getCurrencyCode(), is("EUR"));
        assertThat(payment.getAmount().getExponent(), is(2L));
        assertThat(payment.getAmount().getValue(), is(2400L));
        assertThat(payment.getAmount().getDebitCreditIndicator(), is(DebitCreditIndicator.CREDIT));
        assertThat(payment.getCardDetailsResultResult().getMaskedCardNumber(), is("444433******1111"));
        assertThat(payment.getCardDetailsResultResult().getExpiryMonth(), is(1L));
        assertThat(payment.getCardDetailsResultResult().getExpiryYear(), is(2020L));
        assertThat(payment.getCardDetailsResultResult().getIssuerCountryCode(), is("N/A"));
        assertThat(payment.getCardDetailsResultResult().getCardHolderName(), is("***"));
        assertThat(payment.getPayoutAuthorisationResult().getAuthorisationId(), is("622206"));
        assertThat(payment.getThreeDSecureResult().getDescription(), is("Cardholder authenticated"));
        assertThat(payment.getThreeDSecureResult().getEci(), is("05"));
        assertThat(payment.getThreeDSecureResult().getCavv(), is("MAAAAAAAAAAAAAAAAAAAAAAAAAA="));
        assertThat(payment.getLastEvent(), is(LastEvent.AUTHORISED));
        assertThat(payment.getAvsResult().getAvsResultCode(), is("E"));
        assertThat(payment.getCvcResult().getDescription(), is("C"));
        assertThat(payment.getAvvResult().getAddressResultCode(), is("B"));
        assertThat(payment.getAvvResult().getPostCodeResultCode(), is("B"));
        assertThat(payment.getAvvResult().getCardHolderNameResultCode(), is("B"));
        assertThat(payment.getAvvResult().getTelephoneResultCode(), is("B"));
        assertThat(payment.getAvvResult().getEmailResultCode(), is("B"));
        assertThat(payment.getRiskScoreResult().getValue(), is(0));
    }

    @Test
    public void cancelled_asExpected() throws WpgMalformedException
    {
        // Given
        InputStream inputStream = XmlNotificationBuilderTest.class.getResourceAsStream("/order-notification/cancelled.txt");

        // When
        XmlNotificationBuilder builder = new XmlNotificationBuilder();
        OrderNotification orderNotification = builder.read(inputStream);

        // Then
        assertNotNull(orderNotification);
        assertThat(orderNotification.getOrderCode(), is("ExampleOrder1"));

        // -- Payment
        Payment payment = orderNotification.getPayments().get(0);
        assertThat(payment.getLastEvent(), is(LastEvent.CANCELLED));
        assertThat(payment.getCvcResult().getDescription(), is("C"));
        assertThat(payment.getAvsResult().getAvsResultCode(), is("E"));

        // -- Journal
        Journal journal = orderNotification.getJournal();
        assertThat(journal.getType(), is("CANCELLED"));
        assertThat(journal.getSent(), is("n"));
        assertThat(journal.getTransactions().size(), is(1));
    }

    @Test
    public void captured_asExpected() throws WpgMalformedException
    {
        // Given
        InputStream inputStream = XmlNotificationBuilderTest.class.getResourceAsStream("/order-notification/captured.txt");

        // When
        XmlNotificationBuilder builder = new XmlNotificationBuilder();
        OrderNotification orderNotification = builder.read(inputStream);

        // Then
        assertNotNull(orderNotification);
        assertThat(orderNotification.getOrderCode(), is("ExampleOrder1"));

        // -- Payment
        Payment payment = orderNotification.getPayments().get(0);
        assertThat(payment.getLastEvent(), is(LastEvent.CAPTURED));

        // -- Journal
        Journal journal = orderNotification.getJournal();
        assertThat(journal.getType(), is("CAPTURED"));
        assertThat(journal.getSent(), is("n"));
        assertThat(journal.getTransactions().size(), is(2));

        JournalTransaction tx1 = journal.getTransactions().get(0);
        assertThat(tx1.getBatchId(), is("29"));
        assertThat(tx1.getType(), is(JournalTransactionType.IN_PROCESS_CAPTURED));
        assertThat(tx1.getAmount().getDebitCreditIndicator(), is(DebitCreditIndicator.CREDIT));
        assertThat(tx1.getAmount().getValue(), is(1000L));
        assertThat(tx1.getAmount().getExponent(), is(2L));
        assertThat(tx1.getAmount().getCurrencyCode(), is("EUR"));

        JournalTransaction tx2 = journal.getTransactions().get(1);
        assertThat(tx2.getBatchId(), is("30"));
        assertThat(tx2.getType(), is(JournalTransactionType.IN_PROCESS_AUTHORISED));
        assertThat(tx2.getAmount().getDebitCreditIndicator(), is(DebitCreditIndicator.DEBIT));
        assertThat(tx2.getAmount().getValue(), is(1000L));
        assertThat(tx2.getAmount().getExponent(), is(2L));
        assertThat(tx2.getAmount().getCurrencyCode(), is("EUR"));

        JournalReference ref = journal.getReferences().get(0);
        assertThat(ref.getType(), is("capture"));
        assertThat(ref.getReference(), is("YourReference"));
    }

    @Test
    public void refund_asExpected() throws WpgMalformedException
    {
        // Given
        InputStream inputStream = XmlNotificationBuilderTest.class.getResourceAsStream("/order-notification/refund.txt");

        // When
        XmlNotificationBuilder builder = new XmlNotificationBuilder();
        OrderNotification orderNotification = builder.read(inputStream);

        // Then
        assertNotNull(orderNotification);
        assertThat(orderNotification.getOrderCode(), is("ExampleOrder1"));

        // -- Payment
        Payment payment = orderNotification.getPayments().get(0);
        assertThat(payment.getLastEvent(), is(LastEvent.SENT_FOR_REFUND));

        // -- Journal
        Journal journal = orderNotification.getJournal();
        assertThat(journal.getType(), is("SENT_FOR_REFUND"));
    }

    @Test
    public void refused_asExpected() throws WpgMalformedException
    {
        // Given
        InputStream inputStream = XmlNotificationBuilderTest.class.getResourceAsStream("/order-notification/refused.txt");

        // When
        XmlNotificationBuilder builder = new XmlNotificationBuilder();
        OrderNotification orderNotification = builder.read(inputStream);

        // Then
        assertNotNull(orderNotification);
        assertThat(orderNotification.getOrderCode(), is("ExampleOrder1"));

        // -- Payment
        Payment payment = orderNotification.getPayments().get(0);
        assertThat(payment.getLastEvent(), is(LastEvent.REFUSED));
        assertThat(payment.getRiskScoreResult().getValue(), is(256));

        // -- Journal
        Journal journal = orderNotification.getJournal();
        assertThat(journal.getType(), is("REFUSED"));
    }

}
