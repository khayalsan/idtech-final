package dev.khayal.idtech.notification.event;

public record PaymentPendingEvent(
		Long orderId
) implements Event {
}
