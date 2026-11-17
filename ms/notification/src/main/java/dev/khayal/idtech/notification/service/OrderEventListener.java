package dev.khayal.idtech.notification.service;

import dev.khayal.idtech.notification.entity.NotificationEntity;
import dev.khayal.idtech.notification.event.*;
import dev.khayal.idtech.notification.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventListener {
	private final NotificationService notificationService;
	private final NotificationMapper notificationMapper;

	@KafkaListener(
			topics = "orders.created",
			groupId = "ms-notification"
	)
	public void onOrderCreated(OrderCreatedEvent event) {
		log.info("Received order created: {}", event);
		notificationService.send(notificationMapper.toNotificationEntity(event));
	}

	@KafkaListener(
			topics = "orders.picked_up",
			groupId = "ms-notification"
	)
	public void onOrderPickedUp(OrderPickedUpEvent event) {
		log.info("Received order picked up: {}", event);
		notificationService.send(notificationMapper.toNotificationEntity(event));
	}
	@KafkaListener(
			topics = "orders.delivered",
			groupId = "ms-notification"
	)
	public void onOrderDelivered(OrderDeliveredEvent event) {
		log.info("Received order delivered: {}", event);
		notificationService.send(notificationMapper.toNotificationEntity(event));
	}
}
