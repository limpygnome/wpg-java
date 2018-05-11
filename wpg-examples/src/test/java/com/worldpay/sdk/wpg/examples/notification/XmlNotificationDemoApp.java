package com.worldpay.sdk.wpg.examples.notification;

import com.worldpay.sdk.wpg.builder.XmlNotificationBuilder;
import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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
                String xml = fromClassPath(xmlExample);
                OrderNotification notification = builder.read(xml);
                System.out.println(notification.toString());
            }
        }
        catch (WpgMalformedXmlException e)
        {
            e.printStackTrace();
        }
    }

    private static String fromClassPath(String path)
    {
        InputStream inputStream = XmlNotificationDemoApp.class.getResourceAsStream(path);
        String xml = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        return xml;
    }

}
