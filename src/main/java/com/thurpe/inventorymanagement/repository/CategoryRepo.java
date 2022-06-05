package com.thurpe.inventorymanagement.repository;

import com.thurpe.inventorymanagement.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);
}
