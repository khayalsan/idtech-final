package dev.khayal.idtech.order.mapper;

import dev.khayal.idtech.order.dto.OrderDto;
import dev.khayal.idtech.order.dto.OrderItemDto;
import dev.khayal.idtech.order.entity.OrderEntity;
import dev.khayal.idtech.order.entity.OrderItemEntity;
import dev.khayal.idtech.order.event.OrderCreatedEvent;
import dev.khayal.idtech.order.event.OrderDeliveredEvent;
import dev.khayal.idtech.order.event.OrderPickedUpEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toEntity(OrderDto orderDto);
    OrderItemEntity toEntity(OrderItemDto orderItemDto);
    OrderDto toDto(OrderEntity orderEntity);

    OrderCreatedEvent toCreatedEvent(OrderEntity orderEntity);
    OrderPickedUpEvent toPickedUpEvent(OrderEntity orderEntity);

    OrderDeliveredEvent toDeliveredEvent(OrderEntity orderEntity);

}

