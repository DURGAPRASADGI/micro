package com.example.customer.service.controller;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.dto.CustomerResponseDto;
import com.example.customer.service.dto.ResponseDto;
import com.example.customer.service.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
@AllArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	private final MessageSource messageSource;
	
	@PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
		
		CustomerResponseDto customer=customerService.createCustomer(customerRequestDto);
		
         ResponseDto<Object> dto=ResponseDto.builder()
					                 .statusCode(HttpStatus.CREATED.value())
					                 .success(Boolean.TRUE)
					                 .timesTamp(LocalDateTime.now())
					                 .mesaage(messageSource.getMessage("customer.successfully.created", null, Locale.getDefault()))
					                 .data(customer)
					                  
					                 .build();
			
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
			
			
			
		
		
		
	}
	
	

}
