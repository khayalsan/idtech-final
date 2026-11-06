package dev.khayal.idtech.payment.service;

import dev.khayal.idtech.payment.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventListener {
	private final PaymentService paymentService;

	@KafkaListener(
			topics = "orders.created",
			groupId = "ms-payment"
	)
	public void onOrderCreated(PaymentEvent event) {
		// TODO: Change to OrderEvent
		paymentService.createPayment();
	}

	@KafkaListener(
			topics = "orders.delivered",
			groupId = "ms-payment"
	)
	public void onOrderDelivered(PaymentEvent event) {
		// TODO: Change to OrderEvent
		paymentService.payCourier();
	}
}
