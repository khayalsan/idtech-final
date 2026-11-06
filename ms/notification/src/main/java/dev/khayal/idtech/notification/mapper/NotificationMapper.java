package dev.khayal.idtech.notification.mapper;

import dev.khayal.idtech.notification.entity.NotificationEntity;
import dev.khayal.idtech.notification.event.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
	@Mapping(target = "message", constant = "Order created")
	NotificationEntity toNotificationEntity(OrderCreatedEvent event);

	@Mapping(target = "message", constant = "Order delivered")
	NotificationEntity toNotificationEntity(OrderDeliveredEvent event);

	@Mapping(target = "message", constant = "Order picked up")
	NotificationEntity toNotificationEntity(OrderPickedUpEvent event);

	@Mapping(target = "message", constant = "Payment pending")
	NotificationEntity toNotificationEntity(PaymentPendingEvent event);

	@Mapping(target = "message", constant = "Payment paid")
	NotificationEntity toNotificationEntity(PaymentPaidEvent event);
}
