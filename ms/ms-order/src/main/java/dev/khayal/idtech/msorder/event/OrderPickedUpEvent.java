package dev.khayal.idtech.msorder.event;

public record OrderPickedUpEvent(
    Long orderId
) implements OrderEvent{
    @Override
    public String topic() {
        return "orders.picked_up";
    }
}
