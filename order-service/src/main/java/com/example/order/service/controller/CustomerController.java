package com.example.order.service.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.service.dto.CustomerResponseDto;
import com.example.order.service.dto.ResponseDto;
import com.example.order.service.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	
	@GetMapping("/customer")
	public ResponseEntity<Object> getCustomer(){
		
		CustomerResponseDto customerResponseDto=customerService.getCustomerData();
		
		ResponseDto<Object> dto=ResponseDto.builder()
				                        .statusCode(HttpStatus.OK.value())
				                        .success(true)
				                        .mesaage("get the data")
				                        .data(customerResponseDto)
				                        .timesTamp(LocalDateTime.now())
				                        .build();
				                        
		 
		return ResponseEntity.status(HttpStatus.OK).body(dto);
		
	}
}
