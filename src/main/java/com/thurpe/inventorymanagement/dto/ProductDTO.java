package com.thurpe.inventorymanagement.dto;

import com.thurpe.inventorymanagement.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@SuperBuilder
public class ProductDTO {
    private Long id;
    private @NotNull String name;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Long categoryId;

    public ProductDTO(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
    }

    public ProductDTO(@NotNull String name, @NotNull double price, @NotNull String description, @NotNull Long categoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

}
