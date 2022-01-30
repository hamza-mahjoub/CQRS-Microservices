package com.example.command.rabbitMq;

import com.example.shared.model.Customer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class Send {
    private static final String QUEUE_NAME = "logs";

    public Send(Customer customer)throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel())
        {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(customer);
            channel.basicPublish("",QUEUE_NAME,null,getByteArray(customer));
            System.out.println("message sent ! ");
        }
        catch(Exception e )
        {
            System.out.println("error : "+e.getMessage());
        }
    }

    public byte[] getByteArray (Customer customer) throws Exception
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(customer);
        return bos.toByteArray();

    }
}
