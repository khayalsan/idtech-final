package dev.khayal.idtech.order.event;

public sealed interface OrderEvent
		permits OrderCancelledEvent, OrderCreatedEvent, OrderDeliveredEvent, OrderPickedUpEvent, OrderRejectedEvent {

    Long orderId();
    String topic();
}
