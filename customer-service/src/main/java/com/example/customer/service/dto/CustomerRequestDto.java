package com.example.customer.service.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class CustomerRequestDto {
	
	@NotBlank(message = "{customer.name.blank}")
	@Pattern(regexp = "^[a-zA-Z .]*$",message = "{customer.name.invalid}")
	@Size(max = 10,message = "{customer.name.size}")
	private String name;
	
	@NotBlank(message = "{customer.email.blank}")
	@Email(message = "{customer.email.invalid}")
	private String email;

	@NotNull(message = "{customer.phone.null}")
	@Min(value = 1000000000, message = "{customer.phone.min}")
	@Digits(integer = 10,fraction = 0,message = "{customer.phone.digits}")
	private Long phoneNumber;
	
	@NotBlank(message = "{customer.address.blank}")
	@Pattern(regexp = "^[a-zA-Z0-9 ./-]*$",message = "{customer.address.invalid}")
	@Size(max = 100,message = "{customer.address.size}")
	private String address;

}
