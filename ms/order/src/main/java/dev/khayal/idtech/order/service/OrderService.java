package dev.khayal.idtech.order.service;

import dev.khayal.idtech.order.exception.OrderNotFoundException;
import dev.khayal.idtech.order.dto.OrderDto;
import dev.khayal.idtech.order.entity.OrderEntity;
import dev.khayal.idtech.order.feign.CourierClient;
import dev.khayal.idtech.order.mapper.OrderMapper;
import dev.khayal.idtech.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static dev.khayal.idtech.order.enums.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderEventProducer eventProducer;
    private final CourierClient courierClient;

    public void createOrder(OrderDto orderDto) {
        var price = calculatePrice(orderDto);
        var deliveryFee = calculateDeliveryFee(orderDto);
        var orderEntity = orderMapper.toEntity(orderDto);
        orderEntity.setStatus(CREATED);
        orderEntity.setPrice(price.add(deliveryFee));
        
        eventProducer.publish(orderMapper.toCreatedEvent(orderRepository.save(orderEntity)));
    }
    public void finishOrder(Long orderId) {
        Long courierId = courierClient.getAvailable(); // TODO: Exception
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
        orderRepository.save(orderEntity);
    }

    // TODO: Listen Delivery Complete ? or /deliver
    public void orderDelivered(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderEntity.setStatus(DELIVERED);
        eventProducer.publish(orderMapper.toDeliveredEvent(orderEntity));
        orderRepository.save(orderEntity);
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
