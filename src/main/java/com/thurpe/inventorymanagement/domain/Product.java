package com.thurpe.inventorymanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thurpe.inventorymanagement.dto.ProductDTO;
import com.thurpe.inventorymanagement.enums.ProductStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Size(min = 3, max = 200)
    private String description;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Cart> carts;

    @CreationTimestamp
    private Instant createdDate;

    @UpdateTimestamp
    private Instant lastModifiedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(ProductDTO productDTO, Category category) {
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
        this.category = category;
    }

    public Product(String name, double price, String description, Category category) {
        super();
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product() {
    }
}
