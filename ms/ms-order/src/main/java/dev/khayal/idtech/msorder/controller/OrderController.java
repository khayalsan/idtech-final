package dev.khayal.idtech.msorder.controller;

import dev.khayal.idtech.msorder.dto.OrderDto;
import dev.khayal.idtech.msorder.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    public void createOrder(@RequestBody @Valid OrderDto orderDto) {
        orderService.createOrder(orderDto);
    }
}
