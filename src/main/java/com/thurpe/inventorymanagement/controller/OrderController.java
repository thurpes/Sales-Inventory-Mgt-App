package com.thurpe.inventorymanagement.controller;


import com.thurpe.inventorymanagement.domain.Customer;
import com.thurpe.inventorymanagement.domain.Order;
import com.thurpe.inventorymanagement.repository.OrderRepo;
import com.thurpe.inventorymanagement.response.ApiResponse;
import com.thurpe.inventorymanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderDTOList = orderRepo.findAll();
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Order>> getOrder(@PathVariable("customerId") Customer customer) {
        List<Order> orderDTOList = orderService.listOrders(customer);
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("customerId") Customer customer)
            {
        orderService.placeOrder(customer);
        return new ResponseEntity<>(new ApiResponse(true, "New order has been placed"), HttpStatus.CREATED);
    }


}
