package com.example.customer.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.model.Customer;
import com.example.customer.service.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
@AllArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	public ResponseEntity<Object> createCustomer(@RequestBody CustomerRequestDto customerRequestDto){
		
		Customer customer=customerService.createCustomer(customerRequestDto);
		
		
		return null;
	}
	
	

}
