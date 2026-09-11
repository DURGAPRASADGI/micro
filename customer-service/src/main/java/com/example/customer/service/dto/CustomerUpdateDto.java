package com.example.customer.service.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class CustomerUpdateDto {
	@NotNull(message = "{customer.id.blank}")
	@Min(value = 1,message = "customer.id.min")
	private Long customerId;
	
	@NotBlank(message = "{Name cannot be blank}")
	@Pattern(regexp = "^[A-Za-z .]+$",message = "{Name should contain only alphabets, spaces, and dot (.)}")
	@Size(max = 100,message = "{customer.name.size}")
	private String name;
	
	@NotBlank(message = "{Email cannot be blank}")
	@Email(message = "{Please enter a valid email address}")
	private String email;
	
	@NotNull(message = "{Phone number cannot be null}")
	@Min(value = 1000000000,message = "{Phone number should have 10 digits}")
	@Digits(integer = 10,fraction = 0,message = "Phone number should contain exactly 10 digits")
	private Long phoneNumber;
	@Pattern(regexp = "^[A-Za-z0-9 ./-]*$",message = "{Address contains invalid characters}")
	@Size(max = 100,message = "{customer.address.size}")
	private String address;
	
	@NotEmpty(message = "{customer.orders.empty}")
	
	private List <@Valid OrderRequestDto> orderRequestDtos;

}
