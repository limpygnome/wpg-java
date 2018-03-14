package com.worldpay.sdk.wpg.notification;

import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class XmlNotificationReader
{

    public OrderNotification read(String xml) throws WpgMalformedXmlException
    {
        try
        {
            XmlBuilder builder = XmlBuilder.parse(xml);
            if (builder.hasE("notify") && builder.hasE("orderStatusEvent"))
            {
                return null;
            }
            else
            {
                throw new WpgMalformedXmlException("Not recognized as order status event");
            }
        }
        catch (WpgRequestException e)
        {
            throw new WpgMalformedXmlException("Failed to read xml", e);
        }
    }

}
