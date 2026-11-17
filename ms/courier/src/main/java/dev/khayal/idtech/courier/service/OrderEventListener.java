package dev.khayal.idtech.courier.service;

import dev.khayal.idtech.courier.event.OrderDeliveredEvent;
import dev.khayal.idtech.courier.event.OrderPickedUpEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventListener {
    private final CourierService courierService;

    @KafkaListener(
        topics = "orders.picked_up",
        groupId = "ms-courier"
    )
    public void onOrderPickedUp(OrderPickedUpEvent event) {
        log.info("Received order picked up event: {}", event);
        courierService.markBusy(event.courierId());
    }

    @KafkaListener(
        topics = "orders.delivered",
        groupId = "ms-courier"
    )
    public void onOrderDelivered(OrderDeliveredEvent event) {
        log.info("Received order delivered event: {}", event);
        courierService.markFree(event.courierId());
    }
}
