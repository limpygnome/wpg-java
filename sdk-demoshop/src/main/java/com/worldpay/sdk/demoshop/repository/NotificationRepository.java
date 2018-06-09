package com.worldpay.sdk.demoshop.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NotificationRepository extends CrudRepository<NotificationItem, UUID>
{

}
