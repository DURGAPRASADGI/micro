package com.example.customer.service.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerRequestDto {
	
	@NotBlank(message = "name cannot be blank or empty")
	@Pattern(regexp = "^[a-zA-Z.]+$",message = "name should accept alphates or .")
	@Size(max = 100,message = "name not exced more than 100 charactors")
	private String name;
	
	@NotBlank(message = "email cannot be empty or blank")
	@Email
	private String email;

	@NotNull(message = "phone Number not Null")
	@Min(value = 1000000000, message = "phone Number should have 10 charctors ")
	@Digits(integer = 10,fraction = 0,message = "phone number accept 10 digits")
	private Long phoneNumber;
	
	@NotBlank(message = "address cannot be blank or Number")
	@Pattern(regexp = "^[a-zA-Z./-]+$",message = "addres accept alphates or . or / or -")
	@Size(max = 100,message = "address cannot be exced more than  100 charactors")
	private String address;

}
