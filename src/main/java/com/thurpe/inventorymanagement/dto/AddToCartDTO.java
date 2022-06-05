package com.thurpe.inventorymanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@SuperBuilder
public class AddToCartDTO {
    private Long id;
    private @NotNull Long productId;
    private @NotNull Integer quantity;
}
