package dev.khayal.idtech.order.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(
    Long orderId,
    BigDecimal price
) implements OrderEvent{
    @Override
    public String topic() {
        return "orders.created";
    }
}
