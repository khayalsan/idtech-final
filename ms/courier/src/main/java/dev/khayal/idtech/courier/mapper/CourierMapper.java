package dev.khayal.idtech.courier.mapper;

import dev.khayal.idtech.courier.dto.CourierRequest;
import dev.khayal.idtech.courier.dto.CourierResponse;
import dev.khayal.idtech.courier.entity.CourierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourierMapper {

	CourierResponse toResponse(CourierEntity entity);
	CourierEntity toEntity(CourierRequest request);
}
