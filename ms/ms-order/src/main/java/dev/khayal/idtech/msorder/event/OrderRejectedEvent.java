package dev.khayal.idtech.msorder.event;

public record OrderRejectedEvent(
    Long orderId
) implements OrderEvent{
    @Override
    public String topic() {
        return "orders.rejected";
    }
}
