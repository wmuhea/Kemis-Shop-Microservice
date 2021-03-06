package com.kemisshop.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@RefreshScope
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}



}
