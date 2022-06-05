package com.thurpe.inventorymanagement.dto;

import com.thurpe.inventorymanagement.domain.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@SuperBuilder
public class PlaceOrderDTO {
    private Long id;
    private @NotNull Long customerId;
    private @NotNull double totalPrice;

    public void PlaceOrderDTO() {
    }

    public void PlaceOrderDTO(Order order) {
        this.setId(order.getId());
        this.setCustomerId(order.getCustomer().getId());
        this.setTotalPrice(order.getTotalPrice());
    }
}
