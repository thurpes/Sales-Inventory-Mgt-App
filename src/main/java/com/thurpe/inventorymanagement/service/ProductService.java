package com.thurpe.inventorymanagement.service;

import com.thurpe.inventorymanagement.domain.Category;
import com.thurpe.inventorymanagement.domain.Product;
import com.thurpe.inventorymanagement.dto.ProductDTO;
import com.thurpe.inventorymanagement.enums.ProductStatus;
import com.thurpe.inventorymanagement.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepository;

    public List<ProductDTO> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product : products) {
            ProductDTO productDTO = getDTOFromProduct(product);
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    public static ProductDTO getDTOFromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO(product);
        return productDTO;
    }

    public static Product getProductFromDTO(ProductDTO productDTO, Category category) {
        Product product = new Product(productDTO, category);
        return product;
    }

    public void createProduct(ProductDTO productDTO, Category category) {
        Product product = getProductFromDTO(productDTO, category);
        product.setStatus(ProductStatus.INSTOCK);
        productRepository.save(product);
    }

    public void updateProduct(Long productID, ProductDTO productDTO, Category category) {
        Product product = getProductFromDTO(productDTO, category);
        product.setId(productID);
        productRepository.save(product);
    }

    public Product getProductById(Long productId) throws IllegalArgumentException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new IllegalArgumentException("Product id is invalid " + productId);
        return optionalProduct.get();
    }

    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new IllegalArgumentException("Product id is invalid " + productId);
        productRepository.deleteById(productId);
    }
}
