package com.thurpe.inventorymanagement.service;

import com.thurpe.inventorymanagement.domain.Category;
import com.thurpe.inventorymanagement.repository.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepo categoryRepo;

    private Category category = new Category();

    @Test
    void createCategory(){
        categoryService.createCategory(category);
        verify(categoryRepo, times(1)).save(any());
    }

    @Test
    void readCategoryByNameNull(){
        when(categoryRepo.findByCategoryName(anyString())).thenReturn(null);
        Category actual = categoryService.readCategory(anyString());

        assertNull(actual);
    }

    @Test
    void readCategoryByName(){
        when(categoryRepo.findByCategoryName(anyString())).thenReturn(category);
        Category actual = categoryService.readCategory(anyString());

        assertNotNull(actual);
    }

    @Test
    void readCategoryByIdNull(){
        when(categoryRepo.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Category> actual = categoryService.readCategory(anyLong());

        assertTrue(actual.isEmpty());
    }

    @Test
    void readCategoryById(){
        when(categoryRepo.findById(anyLong())).thenReturn(Optional.of(category));
        Optional<Category> actual = categoryService.readCategory(anyLong());

        assertTrue(actual.isPresent());
    }

    @Test
    void updateCategory(){
        category.setCategoryName("old name");
        Category categoryUpdate = new Category();
        categoryUpdate.setCategoryName("new Name");
        when(categoryRepo.findById(any())).thenReturn(Optional.of(category));
        categoryService.updateCategory(1L, categoryUpdate);
        verify(categoryRepo, times(1)).save(argThat(savedCategory->{
            assertEquals(categoryUpdate.getCategoryName(), savedCategory.getCategoryName());
            return true;
        }));
    }

    @Test
    void listCategories(){
        Category another = new Category();
        when(categoryRepo.findAll()).thenReturn(List.of(category, another));

        List<Category> actual = categoryService.listCategories();

        assertEquals(List.of(category, another), actual);


    }
}
