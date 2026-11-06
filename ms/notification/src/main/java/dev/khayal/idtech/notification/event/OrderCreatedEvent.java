package dev.khayal.idtech.notification.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(
		Long orderId,
		BigDecimal price
) implements Event {
}
