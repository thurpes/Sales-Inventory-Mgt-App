package com.thurpe.inventorymanagement.controller;

import com.thurpe.inventorymanagement.domain.Customer;
import com.thurpe.inventorymanagement.response.ApiResponse;
import com.thurpe.inventorymanagement.service.CustomerService;
import com.thurpe.inventorymanagement.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping()
    private ResponseEntity<List<Customer>> findAllCustomers(){
        List<Customer> body = customerService.listCustomers();
        return new ResponseEntity<List<Customer>>(body, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    private Customer findCustomer(@PathVariable("customerId") Long customerId){
        return customerService.getById(customerId).get();
    }

    @PostMapping()
    private ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody Customer customer){
        if (Helper.notNull(customerService.getById(customer.getPhone()))){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Customer already exists"), HttpStatus.CONFLICT);
        }
        customerService.createCustomer(customer);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the customer"), HttpStatus.CREATED);
    }
}
