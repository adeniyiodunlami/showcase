package com.orion.app;

import com.dto.SampleConsumerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleConsumer
{
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/employee")
	public SampleConsumerDto getPerson(int id)
	{

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");

		ResponseEntity<SampleConsumerDto> result = restTemplate.exchange(
			"http://localhost:8081/employee/"+id,
			HttpMethod.GET,
			new HttpEntity<>(httpHeaders),
			SampleConsumerDto.class);

		return result.getBody();
	}
}
