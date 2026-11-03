package dev.khayal.idtech.msorder.service;

import dev.khayal.idtech.msorder.exception.OrderNotFoundException;
import dev.khayal.idtech.msorder.dto.OrderDto;
import dev.khayal.idtech.msorder.entity.OrderEntity;
import dev.khayal.idtech.msorder.mapper.OrderMapper;
import dev.khayal.idtech.msorder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static dev.khayal.idtech.msorder.enums.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderEventProducer eventProducer;

    public void createOrder(OrderDto orderDto) {
        BigDecimal price = calculatePrice(orderDto);
        BigDecimal deliveryFee = calculateDeliveryFee(orderDto);
        OrderEntity orderEntity = orderMapper.toEntity(orderDto);
        orderEntity.setStatus(CREATED);
        orderEntity.setPrice(price.add(deliveryFee));

        orderRepository.save(orderEntity);
        eventProducer.publish(orderMapper.toCreatedEvent(orderEntity));
    }
    public void finishOrder(Long orderId) {
        // TODO: Get Available Courier feign
        Long courierId = 0L;
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        if (courierId == null) {
            orderEntity.setStatus(REJECTED);
        }
        else {
            if (orderEntity.getStatus() == CREATED) {
                orderEntity.setStatus(PICKED_UP);
                orderEntity.setCourierId(courierId);
                eventProducer.publish(orderMapper.toPickedUpEvent(orderEntity));
            }
        }
    }

    // TODO: Listen Delivery Complete ? or /deliver
    public void orderDelivered(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderEntity.setStatus(DELIVERED);
        eventProducer.publish(orderMapper.toDeliveredEvent(orderEntity));
    }

    private BigDecimal calculatePrice(OrderDto orderDto) {
        // TODO: get item price
        BigDecimal itemPrice = new BigDecimal(1);
        return orderDto.items().stream()
            .map(item -> itemPrice.multiply(new BigDecimal(item.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateDeliveryFee(OrderDto orderDto) {
        // TODO: calculate fee per item
        BigDecimal itemFee = new BigDecimal("0.1");
        return orderDto.items().stream()
            .map(item -> itemFee.multiply(new BigDecimal(item.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
