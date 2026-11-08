package dev.khayal.idtech.payment.event;

public interface PaymentEvent {
	String topic();
	Long paymentId();
}
