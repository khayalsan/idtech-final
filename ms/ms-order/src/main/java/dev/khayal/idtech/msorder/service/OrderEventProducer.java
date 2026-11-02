package dev.khayal.idtech.msorder.service;


import dev.khayal.idtech.msorder.event.*;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CompletableFuture<SendResult<String, Object>> publish(
        OrderEvent event
    ) {
        return kafkaTemplate.send(
            event.topic(),
            event.orderId().toString(),
            event
        );
    }
}
