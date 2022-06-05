package com.thurpe.inventorymanagement.dto;

import com.thurpe.inventorymanagement.domain.Cart;
import com.thurpe.inventorymanagement.domain.Product;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
public class CartItemDTO {
    private Long id;
    private @NotNull Integer quantity;
    private @NotNull Product product;

    public CartItemDTO() {
    }

    public CartItemDTO(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productName=" + product.getName() +
                '}';
    }
}
