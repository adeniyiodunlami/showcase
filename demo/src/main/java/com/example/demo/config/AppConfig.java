package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	@Value("${fiix.api.connect-mgmt-service.endpoint}")
	private String connectManagementHost;

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
			.baseUrl(connectManagementHost)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}
}
