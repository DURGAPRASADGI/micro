package com.example.customer.service.mapper;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.dto.CustomerResponseDto;
import com.example.customer.service.model.Customer;

public class CustomerMapper {
	
	
	private CustomerMapper() {
		
	}

	public static Customer toEntity(CustomerRequestDto customerRequestDto,Customer customer) {
		customer.setName(customerRequestDto.getName());
		customer.setEmail(customerRequestDto.getEmail());
		customer.setPhoneNumber(customerRequestDto.getPhoneNumber());
		customer.setAddress(customerRequestDto.getAddress());
		return customer;
	}
	
	
	public static CustomerResponseDto toDto(Customer customer) {
		return CustomerResponseDto.builder()
				                  .customerId(customer.getCustomerId())
				                  .name(customer.getName())
				                  .email(customer.getEmail())
				                  .phoneNumber(customer.getPhoneNumber())
				                  .address(customer.getAddress())
				                  .build();
	}
	
	
	
	
}
