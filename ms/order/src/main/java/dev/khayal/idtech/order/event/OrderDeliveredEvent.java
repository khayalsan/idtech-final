package dev.khayal.idtech.order.event;

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
