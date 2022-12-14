### Links used :
1. http://localhost:8000/exchange/USD/INR
2. http://localhost:8100/conversion/USD/INR/1000
3. http://localhost:8100/conversion-feign/USD/INR/100
4. http://localhost:8765/currency-conversion-service/conversion/USD/INR/1000
5. http://localhost:8765/currency-exchange-service/exchange/USD/INR
6. http://localhost:8765/currency-conversion-service/conversion-feign/USD/INR/1000

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

### Feign 
 - It is used to ease the process of using another service to get response and reduce the amount of code we need to write.
 
#### Dependency
```
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

#### Without Feign : 
```
@GetMapping("conversion/{from}/{to}/{quantity}")
public CurrencyConversionEntity convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {

	Map<String,String> uriVariables = new HashMap<>();
	uriVariables.put("from", from);
	uriVariables.put("to", to);
	ResponseEntity<CurrencyConversionEntity> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/exchange/{from}/{to}", CurrencyConversionEntity.class,uriVariables);

	CurrencyConversionEntity response = responseEntity.getBody();
	return new CurrencyConversionEntity(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());	
}
```
 
#### With Feign : 
Application File :
 ```
@SpringBootApplication
@EnableFeignClients
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

}
 
 ```
 Controller : 
 ```
@GetMapping("conversion-feign/{from}/{to}/{quantity}")
public CurrencyConversionEntity convertCurrencyUsingFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {

	CurrencyConversionEntity response = proxy.retrieveExchangeValue(from, to);
	return new CurrencyConversionEntity(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());	
}
```

Feign Proxy File : 
```
@FeignClient(name="currency-exchange-service",url="localhost:8000")
public interface CurrencyExchangeServiceProxy {
	
	@GetMapping(path="/exchange/{from}/{to}")
	public CurrencyConversionEntity retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
```

 ### API Gateway Filter 

1. Local Filter : 
```
 package com.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f->f
							.addRequestHeader("MyHeader","MyURI")
							.addRequestParameter("Param", "My Value"))
						.uri("http://localhost:8000/exchange/USD/INR"))
				.route(p->p.path("lb://currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(p->p.path("lb://currency-conversion/**")
						.uri("/lb://currency-conversion"))
				.route(p->p.path("currrency-conversion-new/**")
						.filters(f->f.rewritePath("/currency-conversion-new","currency-conversion-feign"))
						.uri("lb://currency-conversion"))
				.build();
	}
}
```

2. Global Filter 
```
package com.microservices.apigateway;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter{

	private org.slf4j.Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Path of the request received ->{}",
				exchange.getRequest().getPath());
		return chain.filter(exchange);
	}
	
}
```

### Docker 
Install Docker application

#### ZipKin
```
docker run -p 9411:9411 openzipkin/zipkin:2.3
```
#### Dependencies 
```
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.amqp</groupId>
	<artifactId>spring-rabbit</artifactId>
</dependency>
```

#### Sampling Configuration(application.properties)
```
spring.sleuth.sampler.probability=1.0
\\ spring.sleuth.sampler.probability=0.4
```




 
 
