package com.example.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommandApplication.class, args);
    }

}
