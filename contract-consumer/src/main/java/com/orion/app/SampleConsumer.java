package com.orion.app;

import com.dto.SampleConsumerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


@Component

public class SampleConsumer
{
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/employee")
	public SampleConsumerDto getPerson(final int id)
	{

		final ResponseEntity<SampleConsumerDto> result = restTemplate.exchange("http://localhost:8081/employee/" + id,
			HttpMethod.GET, null, SampleConsumerDto.class);

		return result.getBody();
	}
}
