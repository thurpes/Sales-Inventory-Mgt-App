package com.thurpe.inventorymanagement.dto;

import com.thurpe.inventorymanagement.domain.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@SuperBuilder
public class OrderDTO {
    private Long id;
    private @NotNull Long customerId;

    public void OrderDTO(Order order) {
        this.setId(order.getId());
//        this.setCustomerId(order.getCustomerId());
    }
}
