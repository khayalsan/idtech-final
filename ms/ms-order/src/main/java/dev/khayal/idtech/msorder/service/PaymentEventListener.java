package dev.khayal.idtech.msorder.service;

import dev.khayal.idtech.msorder.event.OrderEvent;
import dev.khayal.idtech.msorder.event.PaymentPaidEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventListener {
	private final OrderService orderService;

	@KafkaListener(
			topics = "payments.paid",
			groupId = "ms-order"
	)
	public void onPaymentPaid(PaymentPaidEvent event) {
		orderService.finishOrder(event.orderId());
	}
}
