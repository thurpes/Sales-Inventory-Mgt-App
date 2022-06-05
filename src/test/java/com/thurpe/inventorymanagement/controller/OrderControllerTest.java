package com.thurpe.inventorymanagement.controller;

import com.thurpe.inventorymanagement.domain.Customer;
import com.thurpe.inventorymanagement.domain.Order;
import com.thurpe.inventorymanagement.repository.OrderRepo;
import com.thurpe.inventorymanagement.response.ApiResponse;
import com.thurpe.inventorymanagement.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderRepo orderRepo;
    @Mock
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setup(){
        order = new Order();
    }

    @Test
    void createOrder(){
        ApiResponse apiResponse = new ApiResponse(true, "New order has been placed");
        ResponseEntity<ApiResponse> response = orderController.placeOrder(new Customer());
        assertEquals(apiResponse, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getOrder(){
        when(orderService.listOrders(any())).thenReturn(List.of(order));
        ResponseEntity<List<Order>> response = orderController.getOrder(new Customer());
        assertEquals(List.of(order), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllOrders(){
        Order another = new Order();
        when(orderRepo.findAll()).thenReturn(List.of(order, another));
        ResponseEntity<List<Order>> response = orderController.getAllOrders();
        assertEquals(List.of(order, another), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
