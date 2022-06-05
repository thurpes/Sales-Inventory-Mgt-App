package com.thurpe.inventorymanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class CartDTO {
    private List<CartItemDTO> cartItems;
    private double totalCost;

    public CartDTO(List<CartItemDTO> cartItemDTOList, double totalCost) {
        this.cartItems = cartItemDTOList;
        this.totalCost = totalCost;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItemDTOList) {
        this.cartItems = cartItemDTOList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
