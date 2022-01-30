package com.example.shared.model;

import com.example.shared.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public CustomerList() {
        customers = new ArrayList<>();
    }

}
