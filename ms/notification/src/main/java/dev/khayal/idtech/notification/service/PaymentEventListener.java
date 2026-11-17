package dev.khayal.idtech.notification.service;

import dev.khayal.idtech.notification.event.PaymentPaidEvent;
import dev.khayal.idtech.notification.event.PaymentPendingEvent;
import dev.khayal.idtech.notification.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentEventListener {
    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;
    @KafkaListener(
        topics = "payments.pending",
        groupId = "ms-notification"
    )
    public void onPaymentPending(PaymentPendingEvent event) {
        log.info("Received pending notification: {}", event);
        notificationService.send(notificationMapper.toNotificationEntity(event));
    }
    @KafkaListener(
        topics = "payments.paid",
        groupId = "ms-notification"
    )
    public void onPaymentPaid(PaymentPaidEvent event) {
        log.info("Received paid notification: {}", event);
        notificationService.send(notificationMapper.toNotificationEntity(event));
    }
}
