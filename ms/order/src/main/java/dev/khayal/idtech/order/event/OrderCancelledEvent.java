package dev.khayal.idtech.order.event;

public record OrderCancelledEvent(
    Long orderId
) implements OrderEvent{

    @Override
    public String topic() {
        return "orders.cancelled";
    }
}
