package com.example.query.controllers;

import com.example.shared.model.Customer;
import com.example.shared.model.CustomerList;
import com.example.query.rabbitMq.Recieve;
import com.example.query.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/findAll")
    public CustomerList findAllCustomers() {
        CustomerList customerList = new CustomerList();
        customerList.setCustomers(repository.findAll());
        return customerList;
    }

    @GetMapping("/deleteAll")
    public void deleteAll() {
        repository.deleteAll();
    }

    @PostMapping("/saveCustomer")
    public int saveCustomer(@RequestBody List<Customer> customers) {
        repository.saveAll(customers);
        return customers.size();
    }

    @GetMapping("/findByFirstName/{firstName}")
    public List<Customer> findByFirstName(@PathVariable String firstName) {
        return repository.findByFirstName(firstName);
    }
}
