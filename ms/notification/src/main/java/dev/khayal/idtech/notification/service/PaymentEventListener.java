package dev.khayal.idtech.notification.service;

import dev.khayal.idtech.notification.event.PaymentPaidEvent;
import dev.khayal.idtech.notification.event.PaymentPendingEvent;
import dev.khayal.idtech.notification.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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
