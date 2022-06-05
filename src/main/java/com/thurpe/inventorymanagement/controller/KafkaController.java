package com.thurpe.inventorymanagement.controller;

import com.thurpe.inventorymanagement.domain.Order;
import com.thurpe.inventorymanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public class KafkaController {

    @Autowired
    KafkaTemplate<Object, List<Order>> kafkaTemplate;

    @Autowired
    OrderService orderService;

    private static final String TOPIC = "Filter";

    @GetMapping("/order-report")
    public String publishMessage(@RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                 @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to)
    {
        List<Order> order = orderService.filterOrders(from, to);

        //publish to kafka listeners
        kafkaTemplate.send(TOPIC, order);
        return "Retrieved Successfully";
    }
}
