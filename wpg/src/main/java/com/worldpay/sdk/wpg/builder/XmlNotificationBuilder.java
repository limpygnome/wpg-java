package com.worldpay.sdk.wpg.builder;

import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.adapter.OrderNotificationXmlAdapter;

/**
 * TODO test
 */
public class XmlNotificationBuilder
{

    public OrderNotification read(String xml) throws WpgMalformedXmlException
    {
        try
        {
            XmlBuilder builder = XmlBuilder.parse(xml);
            OrderNotification notification = OrderNotificationXmlAdapter.orderNotification(builder);
            return notification;
        }
        catch (WpgRequestException e)
        {
            throw new WpgMalformedXmlException("Failed to read xml", e);
        }
    }

}
