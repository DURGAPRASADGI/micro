package com.example.customer.service.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
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
public class PaginationDto {
	@Email(message = "{customer.email.invalid}")
	private String email;

	@Min(value = 1000000000, message = "{customer.phone.min}")
	@Digits(integer = 10,fraction = 0,message = "{customer.phone.digits}")
	private Long phoneNumber;
	
    private int pageNo;
	
	private int size=2;
	
	

}
