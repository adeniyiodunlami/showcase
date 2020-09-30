package com.orion.app;

import com.dto.SampleDto;
import com.orion.app.controller.EvenOddController;
import com.orion.app.controller.SampleController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseTestClass
{
	@Autowired
	private EvenOddController evenOddController;

	@Autowired
	SampleController sampleController;

	@MockBean
	private SampleService sampleService;

	@Before
	public void setUp() {
		StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(evenOddController);
		RestAssuredMockMvc.standaloneSetup((standaloneMockMvcBuilder));

		final SampleDto employee = new SampleDto(1, "Jane", "Doe", 123000.00, "M");
		Mockito.when(this.sampleService.findById(1)).thenReturn(Optional.of(employee));
		RestAssuredMockMvc.standaloneSetup(this.sampleController);


	}
}
