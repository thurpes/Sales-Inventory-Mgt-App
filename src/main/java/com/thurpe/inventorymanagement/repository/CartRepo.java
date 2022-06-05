package com.thurpe.inventorymanagement.repository;

import com.thurpe.inventorymanagement.domain.Cart;
import com.thurpe.inventorymanagement.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findAllByCustomerOrderByCreatedDateDesc(Customer customer);

    List<Cart> deleteByCustomer(Customer customer);
}
