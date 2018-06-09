package com.worldpay.sdk.wpg.examples.notification;

import com.worldpay.sdk.wpg.builder.XmlNotificationBuilder;
import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;

import java.io.InputStream;

/**
 * This demo prints out examples of received order notifications.
 */
public class XmlNotificationDemoApp
{

    public static void main(String[] args)
    {
        try
        {
            XmlNotificationBuilder builder = new XmlNotificationBuilder();

            String[] xmlExamples = new String[] {
                    "/order-notification/authorised.txt",
                    "/order-notification/cancelled.txt",
                    "/order-notification/captured.txt",
                    "/order-notification/refund.txt",
                    "/order-notification/refused.txt"
            };

            for (String xmlExample : xmlExamples)
            {
                InputStream inputStream = fromClassPath(xmlExample);
                OrderNotification notification = builder.read(inputStream);
                System.out.println(notification.toString());
            }
        }
        catch (WpgMalformedXmlException e)
        {
            e.printStackTrace();
        }
    }

    private static InputStream fromClassPath(String path)
    {
        InputStream inputStream = XmlNotificationDemoApp.class.getResourceAsStream(path);
        return inputStream;
    }

}
