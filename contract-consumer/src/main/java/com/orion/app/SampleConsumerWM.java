package com.orion.app;

import com.dto.SampleConsumerDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Component
public class SampleConsumerWM
{
	final RestTemplate restTemplate = new RestTemplate();

	public SampleConsumerDto getPerson(@RequestParam("id")int id)
	{

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");

		ResponseEntity<SampleConsumerDto> result = restTemplate.exchange(
			"http://localhost:8088/employee/"+id,
			HttpMethod.GET,
			new HttpEntity<>(httpHeaders),
			SampleConsumerDto.class);


		return result.getBody();
	}
}
