package dev.khayal.idtech.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import dev.khayal.idtech.order.entity.OrderItemEntity;

/** DTO for {@link OrderItemEntity}*/
public record OrderItemDto(
    @NotNull Long itemId,
    @NotNull @Positive Integer quantity
) { }
