package dev.khayal.idtech.order.service;


import dev.khayal.idtech.order.event.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CompletableFuture<SendResult<String, Object>> publish(
        OrderEvent event
    ) {
        log.info("Publishing order event: {}", event);
        return kafkaTemplate.send(
            event.topic(),
            event.orderId().toString(),
            event
        );
    }
}
