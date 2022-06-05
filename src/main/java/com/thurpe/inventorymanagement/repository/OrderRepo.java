package com.thurpe.inventorymanagement.repository;

import com.thurpe.inventorymanagement.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerIdOrderByCreatedDateDesc(Long customerId);
    List<Order> findAllByCreatedDateBetween(LocalDate from, LocalDate to);

}
