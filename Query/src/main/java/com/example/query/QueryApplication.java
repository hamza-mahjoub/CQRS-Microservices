package com.example.query;

import com.example.query.rabbitMq.Recieve;
import com.example.query.repositories.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class QueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }
}
