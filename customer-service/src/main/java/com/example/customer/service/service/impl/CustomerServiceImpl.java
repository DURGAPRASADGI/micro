package com.example.customer.service.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.dto.CustomerResponseDto;
import com.example.customer.service.dto.CustomerUpdateDto;
import com.example.customer.service.exception.ResourceNotFound;
import com.example.customer.service.exception.ResuorceAlreadyExist;
import com.example.customer.service.mapper.CustomerMapper;
import com.example.customer.service.model.Customer;
import com.example.customer.service.model.Order;
import com.example.customer.service.model.OrderItem;
import com.example.customer.service.repository.CustomerRepo;
import com.example.customer.service.service.CustomerService;
import com.example.customer.service.util.CustomerUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepo customerRepo;
	private final MessageSource messageSource;
	

	@Override
	@Transactional
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

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "customerResponseDto",key = "#p0")
	public CustomerResponseDto getAllCustomer(Long id) {

	 
	    // 2. Cache miss
	    Customer customer = customerRepo.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException("Data not found"));

	    CustomerResponseDto customerResponseDto =
	            CustomerMapper.toDto(customer);

	    log.info("Get customer from DB");

	   

	    return customerResponseDto;
	}

	@Override
    @CachePut(value = "customerResponseDto",key = "#p0.email + #p0.phoneNumber")
	@Transactional
	public CustomerResponseDto updateCustomer(
	        CustomerUpdateDto customerUpdateDto) {

	    Map<String, Object> customerMap =
	            customerRepo.findByEmailAndPhoneNumber(
	                    customerUpdateDto.getEmail(),
	                    customerUpdateDto.getPhoneNumber());

	    if (customerMap.isEmpty()) {
	        throw new ResourceNotFound(
	                String.format(
	                        "%s email and %d phoneNumber not found",
	                        customerUpdateDto.getEmail(),
	                        customerUpdateDto.getPhoneNumber()));
	    }

	    Customer customer = CustomerUtil.getCustomer(customerMap);

	    customer.setCustomerId(customerUpdateDto.getCustomerId());
	    customer.setName(customerUpdateDto.getName());
	    customer.setEmail(customerUpdateDto.getEmail());
	    customer.setPhoneNumber(customerUpdateDto.getPhoneNumber());
	    customer.setAddress(customerUpdateDto.getAddress());

	    List<Order> orders =
	            customerUpdateDto.getOrderRequestDtos()
	                    .stream()
	                    .map(ord -> {

	                        Order order =
	                                CustomerMapper.orderRequestDtoToEntity(
	                                        ord,
	                                        new Order());

	                        List<OrderItem> orderItems =
	                                ord.getOrderItemRequestDtos()
	                                        .stream()
	                                        .map(item -> {

	                                            OrderItem orderItem =
	                                                    CustomerMapper
	                                                            .orderItemRequestDtoToEntity(
	                                                                    item,
	                                                                    new OrderItem());

	                                            orderItem.setOrder(order);

	                                            return orderItem;
	                                        })
	                                        .collect(Collectors.toList());

	                        order.setOrderItems(orderItems);
	                        order.setCustomer(customer);

	                        return order;
	                    })
	                    .collect(Collectors.toList());

	    customer.setOrders(orders);

	    Customer savedCustomer = customerRepo.save(customer);

	    return CustomerMapper.toDto(savedCustomer);
	}

	@Override
	@CacheEvict(value = "customerResponseDto",key = "#p0")
	@Transactional
	public boolean deleteCustomer(Long customerId) {
		// TODO Auto-generated method stub
		
		if(customerRepo.findById(customerId).isEmpty()) return false;
		
		customerRepo.deleteById(customerId);
		
		return true;
	}
	
	

}
