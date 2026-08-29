package com.example.customer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerResponseDto {
	private Long customerId;
	private String name;
	private String email;
	private Long phoneNumber;
	private String address;

}
