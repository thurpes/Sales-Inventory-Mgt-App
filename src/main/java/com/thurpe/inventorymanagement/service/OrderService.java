package com.thurpe.inventorymanagement.service;

import com.thurpe.inventorymanagement.domain.Customer;
import com.thurpe.inventorymanagement.domain.Order;
import com.thurpe.inventorymanagement.domain.OrderItem;
import com.thurpe.inventorymanagement.dto.CartDTO;
import com.thurpe.inventorymanagement.dto.CartItemDTO;
import com.thurpe.inventorymanagement.repository.OrderItemsRepository;
import com.thurpe.inventorymanagement.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    KafkaTemplate<String, OrderItem> kafkaTemplate;

    public void placeOrder(Customer customer) {
        CartDTO cartDTO = cartService.listCartItems(customer);

        List<CartItemDTO> cartItemDTOList = cartDTO.getCartItems();

        // create the order and save it
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setCustomer(customer);
        newOrder.setTotalPrice(cartDTO.getTotalCost());
        orderRepository.save(newOrder);

        for (CartItemDTO cartItemDTO : cartItemDTOList) {
            // create orderItem and save each one
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDTO.getProduct().getPrice());
            orderItem.setProduct(cartItemDTO.getProduct());
            orderItem.setQuantity(cartItemDTO.getQuantity());
            orderItem.setOrder(newOrder);
            orderItemsRepository.save(orderItem);

            //publish to kafka listeners
            String TOPIC = "New Orders";
            kafkaTemplate.send(TOPIC, orderItem);
        }
        cartService.deleteUserCartItems(customer);
    }

    public Order getOrder(Long orderId) throws IllegalArgumentException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new IllegalArgumentException("Order not found");
    }

    public List<Order> listOrders(Customer customer) {
        List<Order> orderList = orderRepository.findAllByCustomerIdOrderByCreatedDateDesc(customer.getId());
        return orderList;
    }

    public List<Order> filterOrders(LocalDate from, LocalDate to) {
        List<Order> orderList = orderRepository.findAllByCreatedDateBetween(from, to);
        return orderList;
    }
}
