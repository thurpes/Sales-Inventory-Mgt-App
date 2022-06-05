package com.thurpe.inventorymanagement.controller;

import com.thurpe.inventorymanagement.domain.Customer;
import com.thurpe.inventorymanagement.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerRepo customerRepo;

    private Customer customer;

    @BeforeEach
    void setup(){
        customer = new Customer();
    }

    @Test
    void createCustomer(){
        when(customerRepo.save(any())).thenReturn(customer);
        Customer actual = customerController.saveCustomer(customer);
        assertEquals(customer, actual);
    }

}
