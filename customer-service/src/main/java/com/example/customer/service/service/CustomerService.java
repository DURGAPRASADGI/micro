package com.example.customer.service.service;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.dto.CustomerResponseDto;

public interface CustomerService {

	CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

}
