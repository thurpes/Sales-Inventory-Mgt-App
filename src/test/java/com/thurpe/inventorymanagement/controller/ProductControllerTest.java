package com.thurpe.inventorymanagement.controller;

import com.thurpe.inventorymanagement.domain.Product;
import com.thurpe.inventorymanagement.repository.ProductRepo;
import com.thurpe.inventorymanagement.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductRepo productRepo;

    private Product product;

    @BeforeEach
    void setup(){
        product = new Product();
    }

    @Test
    void createProduct(){
        when(productRepo.save(any())).thenReturn(product);
        Product actual = productController.saveProduct(product);
        assertEquals(product, actual);
    }

    @Test
    void updateProduct(){
        product.setName("old name");
        Product productUpdate = new Product();
        productUpdate.setName("new Name");
        when(productRepo.findById(any())).thenReturn(Optional.of(product));
        productController.updateProduct(1L, productUpdate);
        verify(productRepo, times(1)).save(argThat(savedProduct->{
            assertEquals(productUpdate.getName(), savedProduct.getName());
            return true;
        }));
    }

    @Test
    void findProduct(){
        when(productRepo.findById(any())).thenReturn(Optional.of(product));
        Product actual = productController.findProduct(1L);
        assertEquals(product, actual);

    }

}
