package dev.khayal.idtech.notification.event;

public record OrderDeliveredEvent(
		Long orderId
) implements Event  {
}
