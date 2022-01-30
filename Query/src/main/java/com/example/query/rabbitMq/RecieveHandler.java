package com.example.query.rabbitMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RecieveHandler implements CommandLineRunner {
    @Autowired
    private Recieve recieve;

    @Override
    public void run(String...args) throws Exception {
        recieve.recieveData();

    }
}