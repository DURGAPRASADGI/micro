package com.example.customer.service.util;

import java.util.Map;

import com.example.customer.service.model.Customer;

public class CustomerUtil {
	
	public  static Customer getCustomer(Map<String, Object> map) {
      	Long customerId =CommonUtil.getLong(map.get("customerId"));
		String name =CommonUtil.getString("name");
		String email=CommonUtil.getString("email");
		Long phoneNumber=CommonUtil.getLong("phoneNumber");
		String address=CommonUtil.getString("address");
		
	  return Customer.builder()
				      .customerId(customerId)
				      .name(name)
				      .email(email)
				      .phoneNumber(phoneNumber)
				      .address(address)
				      .build();
	}
	
	  


}
