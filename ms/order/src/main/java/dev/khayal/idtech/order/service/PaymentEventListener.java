package dev.khayal.idtech.order.service;

import dev.khayal.idtech.order.event.OrderEvent;
import dev.khayal.idtech.order.event.PaymentPaidEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventListener {
	private final OrderService orderService;

	@KafkaListener(
			topics = "payments.paid",
			groupId = "order"
	)
	public void onPaymentPaid(PaymentPaidEvent event) {
		orderService.finishOrder(event.orderId());
	}
}
