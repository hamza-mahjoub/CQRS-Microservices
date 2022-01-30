package com.example.command.controllers;

import com.example.shared.model.Customer;
import com.example.command.rabbitMq.Send;
import com.example.command.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping("/add")
    @ResponseBody
    public Customer saveCustomer(@RequestBody Customer customer) {
        Customer result = repository.save(customer);
        try{
            new Send(result);
            return repository.save(customer);
        }catch (Exception e){
            System.out.println("error 1 "+e.getMessage());
        }
        return null;
    }

    @GetMapping("/deleteAll")
    public void deleteAll() {
        repository.deleteAll();
    }
}
