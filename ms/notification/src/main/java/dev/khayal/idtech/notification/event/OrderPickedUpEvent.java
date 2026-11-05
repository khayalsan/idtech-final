package dev.khayal.idtech.notification.event;

import java.math.BigDecimal;

public record OrderPickedUpEvent(
		Long orderId
) implements Event {
}
