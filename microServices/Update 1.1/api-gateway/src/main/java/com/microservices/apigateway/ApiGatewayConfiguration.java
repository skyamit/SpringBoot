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
