package com.example.customer.service.service.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.dto.CustomerResponseDto;
import com.example.customer.service.exception.ResuorceAlreadyExist;
import com.example.customer.service.mapper.CustomerMapper;
import com.example.customer.service.model.Customer;
import com.example.customer.service.repository.CustomerRepo;
import com.example.customer.service.service.CustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepo customerRepo;
	private final MessageSource messageSource;

	@Override
	public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
		// TODO Auto-generated method stub
		
		if(customerRepo.emailAlreadyExist(customerRequestDto.getEmail())) {
			throw new ResuorceAlreadyExist(messageSource.getMessage("customer.email.already.exist", new Object[] {customerRequestDto.getEmail()}, Locale.getDefault()));
		}
		
		if(customerRepo.findByPhoneNumber(customerRequestDto.getPhoneNumber()).isPresent()) {
			 throw new ResuorceAlreadyExist(messageSource.getMessage("customer.phone.number.exist", new Object[] {customerRequestDto.getPhoneNumber()},Locale.getDefault()));
		}
		
		Customer customer=CustomerMapper.toEntity(customerRequestDto, new Customer());
		
		Customer savedCustomer=  customerRepo.save(customer);
		
		CustomerResponseDto customerResponseDto= CustomerMapper.toDto(savedCustomer);
		
		
		return customerResponseDto;
	}

}
