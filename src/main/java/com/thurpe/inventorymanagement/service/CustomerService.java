package com.thurpe.inventorymanagement.service;

import com.thurpe.inventorymanagement.domain.Customer;
import com.thurpe.inventorymanagement.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    CustomerRepo customerRepository;

    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(Long customerId) { return customerRepository.findById(customerId); }

    public void createCustomer(Customer customer) { customerRepository.save(customer); }
}
