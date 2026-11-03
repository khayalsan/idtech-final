package dev.khayal.idtech.msorder.event;

public record OrderPickedUpEvent(
    Long orderId,
    Long courierId
) implements OrderEvent{
    @Override
    public String topic() {
        return "orders.picked_up";
    }
}
