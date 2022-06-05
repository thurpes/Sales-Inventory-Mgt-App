package com.thurpe.inventorymanagement.service;

import com.thurpe.inventorymanagement.domain.Category;
import com.thurpe.inventorymanagement.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepo categoryrepository;

    public CategoryService(CategoryRepo categoryrepository) {
        this.categoryrepository = categoryrepository;
    }

    public List<Category> listCategories() {
        return categoryrepository.findAll();
    }

    public void createCategory(Category category) {
        categoryrepository.save(category);
    }

    public Category readCategory(String categoryName) {
        return categoryrepository.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Long categoryId) {
        return categoryrepository.findById(categoryId);
    }

    public void updateCategory(Long categoryId, Category newCategory) {
        Category category = categoryrepository.findById(categoryId).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setProducts(newCategory.getProducts());

        categoryrepository.save(category);
    }
}
