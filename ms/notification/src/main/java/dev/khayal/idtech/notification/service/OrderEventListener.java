package dev.khayal.idtech.notification.service;

import dev.khayal.idtech.notification.entity.NotificationEntity;
import dev.khayal.idtech.notification.event.*;
import dev.khayal.idtech.notification.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
		notificationService.send(notificationMapper.toNotificationEntity(event));
	}

	@KafkaListener(
			topics = "orders.picked_up",
			groupId = "ms-notification"
	)
	public void onOrderPickedUp(OrderPickedUpEvent event) {
		notificationService.send(notificationMapper.toNotificationEntity(event));
	}
	@KafkaListener(
			topics = "orders.delivered",
			groupId = "ms-notification"
	)
	public void onOrderDelivered(OrderDeliveredEvent event) {
		notificationService.send(notificationMapper.toNotificationEntity(event));
	}
	@KafkaListener(
			topics = "payments.pending",
			groupId = "ms-notification"
	)
	public void onPaymentPending(PaymentPendingEvent event) {
		notificationService.send(notificationMapper.toNotificationEntity(event));
	}
	@KafkaListener(
			topics = "payments.paid",
			groupId = "ms-notification"
	)
	public void onPaymentPaid(PaymentPaidEvent event) {
		notificationService.send(notificationMapper.toNotificationEntity(event));
	}
}
