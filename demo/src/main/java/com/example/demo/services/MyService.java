package com.example.demo.services;

import com.example.demo.model.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MyService {

	private String monoUrl = "/getMono";
	private String fluxUrl = "/getFlux";

	private final WebClient webClient;

	@Autowired
	public MyService(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<MyModel> getMono() {
		return this.webClient
			.get()
			.uri(monoUrl)
			.retrieve()
			.bodyToMono(MyModel.class);
	}

	public Flux<MyModel> getFlux() {
		return this.webClient
			.get()
			.uri(fluxUrl)
			.retrieve()
			.bodyToFlux(MyModel.class)
			.log();
	}
}
