package dev.khayal.idtech.msorder.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import dev.khayal.idtech.msorder.entity.OrderItemEntity;

/** DTO for {@link OrderItemEntity}*/
public record OrderItemDto(
    @NotNull Long itemId,
    @NotNull @Positive Integer quantity
) { }
