package dev.khayal.idtech.msorder.service;

import static dev.khayal.idtech.msorder.enums.OrderStatus.CREATED;

import dev.khayal.idtech.msorder.dto.OrderDto;
import dev.khayal.idtech.msorder.entity.OrderEntity;
import dev.khayal.idtech.msorder.mapper.OrderMapper;
import dev.khayal.idtech.msorder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public void createOrder(OrderDto orderDto) {
        BigDecimal price = calculatePrice(orderDto);
        BigDecimal deliveryFee = calculateDeliveryFee(orderDto);
        OrderEntity orderEntity = orderMapper.toEntity(orderDto);
        orderEntity.setStatus(CREATED);
        orderEntity.setPrice(price.add(deliveryFee));

        orderRepository.save(orderEntity);
        // TODO: publish(ORDER_CREATED)
    }

    BigDecimal calculatePrice(OrderDto orderDto) {
        // TODO: get item price
        BigDecimal itemPrice = new BigDecimal(1);
        return orderDto.items().stream()
            .map(item -> itemPrice.multiply(new BigDecimal(item.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    BigDecimal calculateDeliveryFee(OrderDto orderDto) {
        // TODO: calculate fee per item
        BigDecimal itemFee = new BigDecimal("0.1");
        return orderDto.items().stream()
            .map(item -> itemFee.multiply(new BigDecimal(item.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
