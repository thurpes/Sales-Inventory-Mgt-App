package com.thurpe.inventorymanagement.controller;

import com.thurpe.inventorymanagement.domain.Category;
import com.thurpe.inventorymanagement.response.ApiResponse;
import com.thurpe.inventorymanagement.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
    @InjectMocks
    private CategoryController categoryController;
    @Mock
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setup(){
        category = new Category("name", "description");
    }

    @Test
    void createCategoryNameAlreadyExists(){
        when(categoryService.readCategory(anyString())).thenReturn(category);
        ApiResponse apiResponse = new ApiResponse(false, "category already exists");
        ResponseEntity<ApiResponse> response = categoryController.createCategory(category);
        assertEquals(apiResponse, response.getBody());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void createCategory(){
        when(categoryService.readCategory(anyString())).thenReturn(null);
        ApiResponse apiResponse = new ApiResponse(true, "created the category");
        ResponseEntity<ApiResponse> response = categoryController.createCategory(category);
        assertEquals(apiResponse, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateCategoryNotExists(){
        when(categoryService.readCategory(anyLong())).thenReturn(Optional.empty());
        ApiResponse apiResponse = new ApiResponse(false, "category does not exist");
        ResponseEntity<ApiResponse> response = categoryController.updateCategory(1L, category);
        assertEquals(apiResponse, response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateCategory(){
        when(categoryService.readCategory(anyLong())).thenReturn(Optional.of(category));
        ApiResponse apiResponse = new ApiResponse(true, "updated the category");
        ResponseEntity<ApiResponse> response = categoryController.updateCategory(1L, category);
        assertEquals(apiResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getCategories(){
        when(categoryService.listCategories()).thenReturn(List.of(category));
        ResponseEntity<List<Category>> response = categoryController.getCategories();

        assertEquals(List.of(category), response.getBody());
    }
}
