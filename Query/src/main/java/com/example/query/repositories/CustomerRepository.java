package com.example.query.repositories;

import com.example.shared.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String>{
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByAge(Integer age);
    List<Customer> findAll();
}
