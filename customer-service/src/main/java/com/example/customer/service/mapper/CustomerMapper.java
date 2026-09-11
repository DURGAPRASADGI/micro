package com.example.customer.service.mapper;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.dto.CustomerResponseDto;
import com.example.customer.service.dto.OrderItemRequestDto;
import com.example.customer.service.dto.OrderRequestDto;
import com.example.customer.service.model.Customer;
import com.example.customer.service.model.Order;
import com.example.customer.service.model.OrderItem;

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
	
	public static OrderItem orderItemRequestDtoToEntity(OrderItemRequestDto itemRequestDto,OrderItem item) {
		item.setProductId(itemRequestDto.getProductId());
		item.setProductName(itemRequestDto.getProductName());
		item.setQuantity(itemRequestDto.getQuantity());
		item.setUnitPrice(itemRequestDto.getUnitPrice());
		return item;
		
		
	}
	
	
	public static Order orderRequestDtoToEntity(OrderRequestDto orderRequestDto,Order order) {
		order.setOrderDate(orderRequestDto.getOrderDate());
		order.setStatus(orderRequestDto.getStatus());
		order.setTotalAmount(orderRequestDto.getTotalAmount());
		return order;
	}
	
	
	
	
}
