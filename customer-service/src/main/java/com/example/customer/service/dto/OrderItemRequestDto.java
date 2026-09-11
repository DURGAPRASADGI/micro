package com.example.customer.service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
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
public class OrderItemRequestDto {
	@NotNull(message = "{orderitem.product.id.blank}")
	@Min(value = 1,message = "{orderitem.product.id.min}")
	private Long productId;
	
	@NotBlank(message = "{orderitem.product.name.blank}")
	@Pattern(regexp = "^[A-Za-z]+$",message = "{orderitem.product.name.invalid}")
	@Size(max = 100,message = "{orderitem.product.name.size}")
	private String productName;
	
	@NotNull(message = "{orderitem.quantity.blank}")
	@Min(value = 1,message = "{orderitem.quantity.min}")
	private Integer quantity;
	
	@NotNull(message = "{orderitem.unitPrice.blank}")
	@DecimalMin(value = "1.0",message = "{orderitem.unitPrice.min}")
	private BigDecimal unitPrice;

}
