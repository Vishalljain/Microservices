package com.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	//u can create here the Resttemplate bean as this class act as a config class so u can create restTemplate bean or u can create a class annotated with @Configuration
	
	@Bean
	@LoadBalanced//we use this because rest template use host name and port to communiccate but we want to use service name so we use this plus it provides load balancing
	 RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
