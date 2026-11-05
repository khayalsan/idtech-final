package dev.khayal.idtech.order.dto;

import dev.khayal.idtech.order.entity.OrderEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * DTO for {@link OrderEntity}
 */
public record OrderDto(
    @Size(min = 2, max = 100) @NotBlank String deliveryAddress,
    List<OrderItemDto> items
) { }