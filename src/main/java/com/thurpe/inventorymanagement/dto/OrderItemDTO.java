package com.thurpe.inventorymanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@SuperBuilder
public class OrderItemDTO {
    private @NotNull double price;
    private @NotNull int quantity;
    private @NotNull int orderId;
    private @NotNull int productId;

    public void OrderItemsDTO () {}

    public void OrderItemsDTO(@NotNull double price, @NotNull int quantity, @NotNull int orderId, @NotNull int productId) {
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }
}
