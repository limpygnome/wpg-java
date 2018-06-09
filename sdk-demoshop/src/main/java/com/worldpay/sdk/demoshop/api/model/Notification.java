package com.worldpay.sdk.demoshop.api.model;

import com.worldpay.sdk.wpg.builder.XmlNotificationBuilder;
import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.exception.WpgException;

import java.util.UUID;

public class Notification
{
    private String id;
    private String orderCode;
    private String xml;

    public Notification(UUID id, String xml) throws WpgException
    {
        OrderNotification notification = new XmlNotificationBuilder().read(xml);

        this.id = id.toString();
        this.orderCode = notification.getOrderCode();
        this.xml = xml;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getXml()
    {
        return xml;
    }

    public void setXml(String xml)
    {
        this.xml = xml;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
