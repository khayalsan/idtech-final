package dev.khayal.idtech.msorder.event;

public record OrderCancelledEvent(
    Long orderId
) implements OrderEvent{

    @Override
    public String topic() {
        return "orders.cancelled";
    }
}
