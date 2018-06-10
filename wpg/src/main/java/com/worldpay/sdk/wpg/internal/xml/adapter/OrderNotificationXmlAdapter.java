package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.domain.journal.Journal;
import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.serializer.JournalSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.PaymentSerializer;

import java.util.ArrayList;
import java.util.List;

public class OrderNotificationXmlAdapter
{

    public static OrderNotification orderNotification(XmlBuilder builder) throws WpgMalformedException, WpgRequestException
    {
        if (builder.hasE("notify") && builder.hasE("orderStatusEvent"))
        {
            String orderCode = builder.a("orderCode");

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
            OrderNotification orderNotification = new OrderNotification(orderCode, payments, journal);
            return orderNotification;
        }
        else
        {
            throw new WpgMalformedException("Not recognized as order status event", null);
        }
    }

}
