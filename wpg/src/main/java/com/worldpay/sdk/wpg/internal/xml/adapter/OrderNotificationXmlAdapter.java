package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.domain.journal.Journal;
import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.serializer.JournalSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.PaymentSerializer;

import java.util.ArrayList;
import java.util.List;

public class OrderNotificationXmlAdapter
{

    public static OrderNotification orderNotification(XmlBuilder builder) throws WpgMalformedXmlException, WpgRequestException
    {
        if (builder.hasE("notify") && builder.hasE("orderStatusEvent"))
        {
            // read payments
            List<XmlBuilder> children = builder.childTags("payment");
            List<Payment> payments = new ArrayList<>(children.size());
            for (XmlBuilder childBuilder : children)
            {
                Payment payment = PaymentSerializer.read(childBuilder);
                payments.add(payment);
            }

            // read journal
            Journal journal = JournalSerializer.read(builder);

            // give back result
            OrderNotification orderNotification = new OrderNotification(payments, journal);
            return orderNotification;
        }
        else
        {
            throw new WpgMalformedXmlException("Not recognized as order status event");
        }
    }

}
