package com.thurpe.inventorymanagement.domain;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @Column(name = "product_id")
    private @NotNull Long productId;

    @Column(name = "quantity")
    private @NotNull int quantity;

    @Column(name = "price")
    private @NotNull double price;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Product product;

    public OrderItem(){}

    public OrderItem(Long orderId, @NotNull Long productId, @NotNull int quantity, @NotNull double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.orderId=orderId;
        this.createdDate = new Date();
    }
}
