package com.thurpe.inventorymanagement.repository;

import com.thurpe.inventorymanagement.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
