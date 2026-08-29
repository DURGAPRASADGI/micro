package com.example.customer.service.service;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.model.Customer;

public interface CustomerService {

	Customer createCustomer(CustomerRequestDto customerRequestDto);

}
