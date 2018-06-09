package com.worldpay.sdk.demoshop.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.UUID;

@Entity
public class NotificationItem
{
    @Id
    private UUID id;
    @Lob
    private String notification;

    public NotificationItem() { }

    public NotificationItem(String notification)
    {
        this.id = UUID.randomUUID();
        this.notification = notification;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getNotification()
    {
        return notification;
    }

    public void setNotification(String notification)
    {
        this.notification = notification;
    }

}
