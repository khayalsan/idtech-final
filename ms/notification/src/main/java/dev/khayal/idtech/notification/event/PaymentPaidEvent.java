package dev.khayal.idtech.notification.event;

public record PaymentPaidEvent(
		Long orderId
) implements Event {
}
