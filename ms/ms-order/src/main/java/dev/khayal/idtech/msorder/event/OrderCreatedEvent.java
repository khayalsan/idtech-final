package dev.khayal.idtech.msorder.event;

public record OrderCreatedEvent(
    Long orderId
) implements OrderEvent{
    @Override
    public String topic() {
        return "orders.created";
    }
}
