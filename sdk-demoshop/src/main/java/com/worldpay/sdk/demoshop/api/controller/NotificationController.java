package com.worldpay.sdk.demoshop.api.controller;

import com.worldpay.sdk.demoshop.api.model.Notification;
import com.worldpay.sdk.demoshop.repository.NotificationItem;
import com.worldpay.sdk.demoshop.repository.NotificationRepository;
import com.worldpay.sdk.wpg.builder.XmlNotificationBuilder;
import com.worldpay.sdk.wpg.domain.notification.OrderNotification;
import com.worldpay.sdk.wpg.exception.WpgException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/notification")
public class NotificationController
{
    private static final Logger LOG = LogManager.getLogger(NotificationController.class.getName());

    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/xml")
    public void receiveXml(HttpServletRequest request, @RequestBody String xml)
    {
        try
        {
            if (xml != null)
            {
                OrderNotification notification = new XmlNotificationBuilder().read(xml);
                LOG.info("received xml notification - ip: {}, orderCode: {}", request.getRemoteAddr(), notification.getOrderCode());
                LOG.debug(xml);

                notificationRepository.save(new NotificationItem(xml));
            }
            else
            {
                LOG.error("received empty notification - ip: {}", request.getRemoteAddr());
            }
        }
        catch (WpgException e)
        {
            LOG.error("Failed to parse XML notification", e);
        }
    }

    @GetMapping
    public List<Notification> fetch()
    {
        Iterable<NotificationItem> items = notificationRepository.findAll();
        List<Notification> result = StreamSupport.stream(items.spliterator(), false)
                .map(item -> transform(item))
                .collect(Collectors.toList());
        return result;
    }

    private Notification transform(NotificationItem item)
    {
        try
        {
            return new Notification(item.getId(), item.getNotification());
        }
        catch (WpgException e)
        {
            LOG.error("failed to parse retrieved notification", e);
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(HttpServletRequest request, @PathVariable UUID id)
    {
        notificationRepository.deleteById(id);
        LOG.info("deleted notification - ip: {}, id: {}", request.getRemoteAddr(), id);
    }

    @DeleteMapping
    public void deleteAll(HttpServletRequest request)
    {
        notificationRepository.deleteAll();
        LOG.info("deleted all notifications - ip: {}", request.getRemoteAddr());
    }

}
