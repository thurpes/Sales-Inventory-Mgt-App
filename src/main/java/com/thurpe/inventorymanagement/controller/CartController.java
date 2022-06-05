package com.thurpe.inventorymanagement.controller;

import com.thurpe.inventorymanagement.domain.Customer;
import com.thurpe.inventorymanagement.dto.CartDTO;
import com.thurpe.inventorymanagement.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping()
    private ResponseEntity<CartDTO> cartItems(@RequestBody Customer customer){
        CartDTO body = cartService.listCartItems(customer);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
