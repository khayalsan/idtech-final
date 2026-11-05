package dev.khayal.idtech.courier.service;

import dev.khayal.idtech.courier.event.OrderDeliveredEvent;
import dev.khayal.idtech.courier.event.OrderPickedUpEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventListener {
    private final CourierService courierService;

    @KafkaListener(
        topics = "orders.picked_up",
        groupId = "ms-courier"
    )
    public void onOrderPickedUp(OrderPickedUpEvent event) {
        courierService.markBusy(event.courierId());
    }

    @KafkaListener(
        topics = "orders.delivered",
        groupId = "ms-courier"
    )
    public void onOrderDelivered(OrderDeliveredEvent event) {
        courierService.markBusy(event.courierId());
    }
}
