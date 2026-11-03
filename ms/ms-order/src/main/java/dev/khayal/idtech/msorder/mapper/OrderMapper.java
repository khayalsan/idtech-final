package dev.khayal.idtech.msorder.mapper;

import dev.khayal.idtech.msorder.dto.OrderDto;
import dev.khayal.idtech.msorder.dto.OrderItemDto;
import dev.khayal.idtech.msorder.entity.OrderEntity;
import dev.khayal.idtech.msorder.entity.OrderItemEntity;
import dev.khayal.idtech.msorder.event.OrderCreatedEvent;
import dev.khayal.idtech.msorder.event.OrderDeliveredEvent;
import dev.khayal.idtech.msorder.event.OrderPickedUpEvent;
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

