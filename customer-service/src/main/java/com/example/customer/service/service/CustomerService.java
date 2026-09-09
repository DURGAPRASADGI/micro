package com.example.customer.service.service;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.dto.CustomerResponseDto;
import com.example.customer.service.dto.CustomerUpdateDto;

import jakarta.validation.Valid;

public interface CustomerService {

	CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

	CustomerResponseDto getAllCustomer(Long id);

	CustomerResponseDto updateCustomer(@Valid CustomerUpdateDto customerUpdateDto);

	boolean deleteCustomer(Long customerId);

}
