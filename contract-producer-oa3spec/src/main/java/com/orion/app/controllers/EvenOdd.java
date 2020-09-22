package com.orion.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvenOdd
{
	@GetMapping("/validate-number")
	public String isNumberEven(@RequestParam("number") Integer number) {
		return number % 2 ==0? "Even": "Odd";
	}
}
