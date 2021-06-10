package com.example.demo.services;

import com.example.demo.model.MyModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
@Import(TestConfiguration.class)
public class MyServiceTests {

	@Autowired
	private MyService myService;

	@ClassRule
	public static WireMockClassRule wireMockRule = new WireMockClassRule(8084);

	@Autowired
	private ObjectMapper objectMapper;

	private MyModel mockResponse;
	private Stream<MyModel> mockResponseStream;
	private List<MyModel> mockResponseList;

	@Before
	public void setUp() {
		mockResponse = new MyModel(1234, "testName");
		mockResponseList = List.of(mockResponse, new MyModel(4567, "testName2"));
		mockResponseStream = mockResponseList.stream();
	}

	@Test
	public void getMonoTest() throws JsonProcessingException {
		String jsonBody = objectMapper.writeValueAsString(mockResponse);

		stubFor(get("/getMono")
			.willReturn(aResponse()
				.withStatus(200)
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBody(jsonBody)
			)
		);

		Mono<MyModel> modelMono = myService.getMono();

		StepVerifier.create(modelMono)
			.assertNext(r -> {
				assertEquals(1234, r.getId());
				assertEquals("testName", r.getName());
			})
			.verifyComplete();
	}

	@Test
	public void getFluxTest() throws JsonProcessingException {
		String jsonBody = objectMapper.writeValueAsString(mockResponseList);

		stubFor(get("/getFlux")
			.willReturn(aResponse()
				.withStatus(200)
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBody(jsonBody)
			)
		);

		Flux<MyModel> modelFlux = myService.getFlux();

		StepVerifier.create(modelFlux)
			.assertNext(r -> {
				assertEquals(1234, r.getId());
				assertEquals("testName", r.getName());
			})
			.assertNext(r -> {
				assertEquals(4567, r.getId());
				assertEquals("testName2", r.getName());
			})
			.verifyComplete();
	}

}
