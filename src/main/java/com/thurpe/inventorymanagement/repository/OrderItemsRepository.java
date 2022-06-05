package com.thurpe.inventorymanagement.repository;

import com.thurpe.inventorymanagement.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}
