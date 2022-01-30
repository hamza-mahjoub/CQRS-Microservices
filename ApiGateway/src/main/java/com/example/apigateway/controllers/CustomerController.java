package com.example.apigateway.controllers;

import com.example.apigateway.models.Customer;
import com.example.apigateway.models.CustomerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

     @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "addCustomer_form";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        CustomerList customers = restTemplate.getForObject("http://QUERY-SERVICE/customer/findAll", CustomerList.class);
        System.out.println(customers.getCustomers());
        model.addAttribute("customers", customers.getCustomers());
        return "customers_list";
    }

   /* @GetMapping("/{customerId}")
        public String getById(@PathVariable("customerId") String customerId, Model model) {
        //List<Customer> customers = restTemplate.getForObject("http://localhost:8082/customer/findAll", List<Customer.class>);
       // model.addAttribute("customer", customer);
       return "customers_list";
    }*/


    @PostMapping("/save")
    public String submitForm(@ModelAttribute("customer") Customer customer, Model model) {

        Customer result = restTemplate.postForObject("http://COMMAND-SERVICE/customer/add", customer, Customer.class);
        System.out.println(result);
        model.addAttribute("customer",result);
        return "register_success";
    }
}
