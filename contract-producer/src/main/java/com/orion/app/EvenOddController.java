package com.orion.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvenOddController
{
	@GetMapping("/validate/even-number")
	public String isNumberPrime(@RequestParam("number") Integer number) {
		return number % 2 ==0? "Even": "Not Even";
	}
}
