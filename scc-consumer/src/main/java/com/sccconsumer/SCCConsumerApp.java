package com.sccconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SCCConsumerApp
{
	public static void main(String args[]) {
		SpringApplication.run(SCCConsumerApp.class, args);
	}

	@Bean RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
