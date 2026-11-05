package dev.khayal.idtech.order.event;

public record OrderRejectedEvent(
    Long orderId
) implements OrderEvent{
    @Override
    public String topic() {
        return "orders.rejected";
    }
}
