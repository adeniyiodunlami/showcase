package com.orion.app.controller;

import com.dto.SampleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController
{

//	@Autowired
//	SampleService sampleService;

	@GetMapping(value = "employee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable("id") int id) {

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(new SampleDto(1, "Jane", "Doe", 123000.00, "M"));
//		Optional<SampleDto> employee = sampleService.findById(id);
//		if (employee.isPresent()) {
//			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(employee.get());
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
	}
}
