package com.example.customer.service.dto;

import java.math.BigDecimal;

import com.example.customer.service.model.OrderStatus;

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
public class PaginationResponseDto {
	private String productName;
	private Integer quantity;
	private BigDecimal unitprice;
	private OrderStatus orderStatus;
	private BigDecimal productPrice;
	private BigDecimal totalOrdedAmount;

}
