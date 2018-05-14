package com.worldpay.sdk.wpg.builder;

import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.adapter.OrderNotificationXmlAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Translates XML from an order / channel notification to a usable Java object.
 */
public class XmlNotificationBuilder
{

    /**
     * Reads content from an input stream.
     *
     * @param inputStream The input stream
     * @return The translated object
     * @throws WpgMalformedXmlException Thrown when the input is malformed / invalid
     */
    public OrderNotification read(InputStream inputStream) throws WpgMalformedXmlException
    {
        String xml = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        OrderNotification orderNotification = read(xml);
        return orderNotification;
    }

    /**
     * Reads content from a string.
     *
     * @param xml The notification, as XML
     * @return The translated object
     * @throws WpgMalformedXmlException Thrown when the input is malformed / invalid
     */
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
