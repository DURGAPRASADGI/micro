package com.example.order.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Example {
	
	@GetMapping("/example")
	public String get() {
		return "i am working";
	}
	

}
