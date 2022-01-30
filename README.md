# CQRS-Microservices

This project implements CQRS pattern using microservices.

## CQRS pattern 

**CQRS** stands for **Command and Query Responsibility Segregation**, a pattern that separates *read* and *update* operations for a *data store*. Implementing CQRS in your
application can maximize its *performance*, *scalability*, and *security*. The flexibility created by migrating to CQRS allows a system to better evolve over time and prevents update commands
from causing merge conflicts at the domain level.

## Technologies 

- The micro framework [Spring boot](https://spring.io/projects/spring-boot).
- The message broker [RabbitMq](https://www.rabbitmq.com/) for events publishing.
- The NoSQL database [MongoDB](https://www.mongodb.com/fr-fr).
- The NoSQL database [ElasticSearch](https://www.elastic.co/fr/).

## Project Architecture
![alt architecture](/assets/architecture.png)

- **Service Registry (Spring Eureka):** The services will register themselves directly when it's up.
- **API Gateway:** This will handle incoming requests and and redirects them to the corresponding registered internal services.
- **Command and Query Services:** The respective MicroServices implementing CQRS.
- **RabbitMQ:** The message broker that will allow us to synchronize between both databases by providing events.
- **MongoDB:** Our command DB, the choice behind this type of database is because of how fast writes are in it.
- **ElasticSearch:** Our query DB, the choice behind this type of database is because of how fast reads are in it.

## How does it work
We create four **spring boot** projects : ApiGateway, Command service, Query service and the Registry.
Our object is : 
```
"customer":{
    "id": "str",
    "firstName": "str",
    "lastName": "str",
    "email": "str",
    "gender": "str",
    "age": "int",
}
```
- The registry which represents a **Eureka discovery server** will hold informations about all client-service applications.
- Upon boot up, every micro service will register into the Eureka server thus the Eureka server will know all the client applications running on each port and IP address.It runs at 
```http://localhost:8761/```

![alt architecture](/assets/eureka.png)

- The api gateway is running at ```http://localhost:8080/``` 
- Use the navbar to either create a customer or select customers.The "ApiGateway" will call the corresponding registered service depending on the type of the action using **RestTemplates**.

![alt Create Customer](/assets/create.png)
![alt Done Creation](/assets/done.png)
![alt List](/assets/list.png)
- In case there is a creation, the **Command Service** will send an event to the **Query Service** via _RabbitMq_ (the _logs_ Queue) so that it can update the ElasticSearch database.


## Side notes 
- Make sure that the mongoDB database is running, execute the command ```mongod``` in your terminal.
- Make sure that the ElasticSearch database is running, execute the command ```elasticsearch.bat``` in your terminal under the **bin** folder where you installed it.
- Check all the dependencies used, _Eureka, ElasticSearch, mongoDb, RabbitMq, web, ..._ .
- To purge the MongoDb database : ```http://localhost:8081/customer/deleteAll```
- To purge the ElasticSearch database : ```http://localhost:8082/customer/deleteAll```
