package com.thurpe.inventorymanagement.controller;

import com.thurpe.inventorymanagement.domain.Category;
import com.thurpe.inventorymanagement.domain.Product;
import com.thurpe.inventorymanagement.dto.ProductDTO;
import com.thurpe.inventorymanagement.response.ApiResponse;
import com.thurpe.inventorymanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    private ResponseEntity<List<ProductDTO>> findAllProducts(){
        List<ProductDTO> body = productService.listProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    private Product findProduct(@PathVariable("productId") Long productId){
        return productService.getProductById(productId);
    }

    @PostMapping()
    private ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO, Category category) {
        productService.createProduct(productDTO, category);
        return new ResponseEntity<>(new ApiResponse(true, "created the product"), HttpStatus.CREATED);
    }

    @PatchMapping("/{productId}")
    private ResponseEntity<ApiResponse> createProduct(@PathVariable Long productId,
                                                      @RequestBody ProductDTO product, Category category) {
        productService.updateProduct(productId, product, category);
        return new ResponseEntity<>(new ApiResponse(true, "Updated the product"), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{productId}")
    private ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new ApiResponse(true, "Product deleted"), HttpStatus.ACCEPTED);
    }

}
