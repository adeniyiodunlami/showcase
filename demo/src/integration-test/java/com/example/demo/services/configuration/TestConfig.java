package com.example.demo.services.configuration;

import com.example.demo.services.MyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@TestConfiguration
public class TestConfig {

	@Value("${fiix.api.connect-mgmt-service.endpoint}")
	private String connectManagementHost;

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
			.baseUrl(connectManagementHost)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

	@Bean
	public MyService getMyService(WebClient webClient) {
		return new MyService(webClient);
	}
}
