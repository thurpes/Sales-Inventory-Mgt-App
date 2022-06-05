package com.thurpe.inventorymanagement.service;

import com.thurpe.inventorymanagement.domain.OrderItem;
import com.thurpe.inventorymanagement.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }
}
