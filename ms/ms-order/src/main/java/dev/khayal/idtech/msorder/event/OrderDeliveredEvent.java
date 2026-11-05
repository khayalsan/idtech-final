package dev.khayal.idtech.msorder.event;

public record OrderDeliveredEvent(
		Long orderId,
		Long courierId
)
	implements OrderEvent {
	@Override
	public String topic() {
		return "orders.delivered";
	}
}
