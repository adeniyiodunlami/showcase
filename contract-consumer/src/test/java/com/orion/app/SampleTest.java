package com.orion.app;

import com.dto.SampleConsumerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import wiremock.org.apache.http.HttpHeaders;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
//@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL,
//	ids = "com.orion.app:contract-producer:+:stubs:8081")
@ComponentScan(basePackages = {"com.orion.app"})
public class SampleTest
{


	@Autowired
	SampleConsumerWM sampleConsumerWM;

	@Autowired
	ObjectMapper objectMapper;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8088));

	@Test
	public void clientShouldRetrunPersonForGivenID() throws Exception {
		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/employee/1")).willReturn(
			WireMock.aResponse()
				.withStatus(200)
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.withBody(jsonForPerson(new SampleConsumerDto(1, "Jane", "Doe")))));
		BDDAssertions.then(this.sampleConsumerWM.getPerson(1).getFname()).isEqualTo("Jane");
	}


	private String jsonForPerson(final SampleConsumerDto person) throws Exception {
		return objectMapper.writeValueAsString(person);
	}
}
