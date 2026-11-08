package dev.khayal.idtech.payment.event;

public record PaymentPendingEvent(
		Long paymentId
) implements PaymentEvent {

	@Override
	public String topic() {
		return "payments.pending";
	}
}
