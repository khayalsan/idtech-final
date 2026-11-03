package dev.khayal.idtech.msorder.event;

public sealed interface OrderEvent
		permits OrderCancelledEvent, OrderCreatedEvent, OrderDeliveredEvent, OrderPickedUpEvent, OrderRejectedEvent {

    Long orderId();
    String topic();
}
