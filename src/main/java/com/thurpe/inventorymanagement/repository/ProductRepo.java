package com.thurpe.inventorymanagement.repository;

import com.thurpe.inventorymanagement.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
