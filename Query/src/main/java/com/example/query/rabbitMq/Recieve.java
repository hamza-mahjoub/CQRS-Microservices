package com.example.query.rabbitMq;

import com.example.query.repositories.CustomerRepository;
import com.example.shared.model.Customer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

@Service
public class Recieve {

    @Autowired
    private CustomerRepository repository;

    private static final String QUEUE_NAME = "logs";

    public Recieve(){}

    public void recieveData() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            byte[] bytearray = delivery.getBody();
            System.out.println(Arrays.toString(delivery.getBody()));
            try {
                Customer customer = deserialize(bytearray);
                System.out.println("after "+customer.toString());
                repository.save(customer);
                System.out.println("Customer Added ! ");
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        };
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag -> {});
    }

    public static Customer deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream in = new ObjectInputStream(bis);
        Customer c = (Customer) in.readObject();
        return c;
    }
}
