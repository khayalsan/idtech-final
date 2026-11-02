package dev.khayal.idtech.msorder.mapper;

import dev.khayal.idtech.msorder.dto.OrderDto;
import dev.khayal.idtech.msorder.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toEntity(OrderDto orderDto);
    OrderDto toDto(OrderEntity orderEntity);
}
