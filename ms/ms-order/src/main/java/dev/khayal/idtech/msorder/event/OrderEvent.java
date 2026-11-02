package dev.khayal.idtech.msorder.event;

public sealed interface OrderEvent
    permits OrderCancelledEvent, OrderCreatedEvent, OrderPickedUpEvent, OrderRejectedEvent {

    Long orderId();
    String topic();
}
