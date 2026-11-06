package dev.khayal.idtech.payment.service;

import dev.khayal.idtech.payment.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PaymentEventProducer {
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public CompletableFuture<SendResult<String, Object>> publish(PaymentEvent event) {
		return kafkaTemplate.send(
				event.topic(),
				event.paymentId().toString(),
				event
		);
	}
}
