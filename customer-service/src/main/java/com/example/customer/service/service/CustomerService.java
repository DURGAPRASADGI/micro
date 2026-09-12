package com.example.customer.service.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.dto.CustomerResponseDto;
import com.example.customer.service.dto.CustomerUpdateDto;
import com.example.customer.service.dto.PaginationDto;
import com.example.customer.service.dto.PaginationResponseDto;

import jakarta.validation.Valid;

public interface CustomerService {

	CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

	CustomerResponseDto getAllCustomer(Long id);

	CustomerResponseDto updateCustomer(@Valid CustomerUpdateDto customerUpdateDto);

	boolean deleteCustomer(Long customerId);

	Page<PaginationResponseDto> getRecords(@Valid PaginationDto paginationDto);

	Page<PaginationResponseDto> getRecordsByUsinJpaQueries(@Valid PaginationDto paginationDto);

	Page<PaginationResponseDto> getRecordsByUsinJpaQueriesDto(@Valid PaginationDto paginationDto);

	CustomerResponseDto getDataBasedOnEmailAndPhoneNumber(String email, Long phoneNumer);
	
	List<String> validateCustomer(String email,Long  phoneNumber);;

}
