package com.orion.app;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.CLASSPATH,
	ids = "com.orion.app:contract-producer:+:stubs:8081")
@ComponentScan(basePackages = {"com.orion.app"})
public class SampleProducerTest
{
	@Autowired
	SampleConsumer sampleConsumer;

//	@Autowired
//	ObjectMapper objectMapper;

//	@Rule
//	public StubRunnerRule stubRunnerRule = new StubRunnerRule()
//		.downloadStub("com.orion.app", "contract-producer", "1.0-SNAPSHOT", "stubs")
//		.withPort(8081)
//		.stubsMode(StubRunnerProperties.StubsMode.CLASSPATH);

	@Test
	public void clientShouldRetrunPersonForGivenID_checkFirsttName() throws Exception {
		BDDAssertions.then(this.sampleConsumer.getPerson(1).getFname()).isEqualTo("Jane");
	}

	@Test
	public void clientShouldRetrunPersonForGivenID_checkLastName() throws Exception {
		BDDAssertions.then(this.sampleConsumer.getPerson(1).getLname()).isEqualTo("Doe");
	}
}
