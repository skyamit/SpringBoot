# Microservices

- Small autonomous services which work together.

1. REST
2. Small Well Chosen Deployable Units
3. Cloud Enabled

#### How does it look?
Microservice1 -> Microservice2 -> Microservice3 -> Microservice4

### Challenges : 
1. Bounded Context
2. Configuration Management
3. Dynamic scale up and scale down
4. Visibility
5. Pack of Cards

### Solution : 
1. Spring Cloud Config Server helps us to keep configuration at one place.
2. Dynamic Scale up and Scale down can be done by Naming Server(Eureka), Ribbon (Client Side Load Balancing), Feign (Easier REST Clients)
3. Visiblity and Monitoring can be done by ZipKin Distributed Tracing and NextFlix API Gateway.
4. Fault Toleerance can be done by Hystrix.

### MicroService Advantage
1. New Technology and Process Adaptation
2. Dynamic Scaling
3. Faster Release Cycle


### Create first Microservice

Dependency needed : 
1. Spring Web
2. Spring Boot Dev Tools
3. Spring Boot Actuator
4. Config Client

#### Microservice will not start if we don't have below line in application.properties file.
```
spring.config.import=optional:configserver:http://localhost:8888
```





