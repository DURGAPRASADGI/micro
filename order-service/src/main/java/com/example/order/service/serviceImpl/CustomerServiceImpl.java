package com.example.order.service.serviceImpl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.order.service.dto.CustomerResponseDto;
import com.example.order.service.service.CustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	
	
	@Override
	@KafkaListener(topics = "customer-topic",groupId = "customer-group-id")
	public CustomerResponseDto getCustomerData() {
		// TODO Auto-generated method stub
		
		return null;
	}

}
