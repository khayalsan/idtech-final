package dev.khayal.idtech.payment.service;

import dev.khayal.idtech.payment.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
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
		log.info("Received order event {}", event);
		paymentService.createPayment();
	}

	@KafkaListener(
			topics = "orders.delivered",
			groupId = "ms-payment"
	)
	public void onOrderDelivered(PaymentEvent event) {
		// TODO: Change to OrderEvent
		log.info("Received order event {}", event);
		paymentService.payCourier();
	}
}
