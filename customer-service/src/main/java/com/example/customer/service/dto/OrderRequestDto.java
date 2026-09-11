package com.example.customer.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.customer.service.model.OrderStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class OrderRequestDto {
	
@NotNull(message = "{order.orderDate.blank}")
@FutureOrPresent(message = "{order.orderDate.invalid}")
private LocalDateTime orderDate;
	

@NotEmpty(message = "{order.orderItem.empty}")

private List<@Valid OrderItemRequestDto> orderItemRequestDtos;   


@NotNull(message = "{order.orderstatus.blank}")
private OrderStatus status;
	
@NotNull(message = "{order.totalAmount.blank}")
@DecimalMin(value = "1.0",message = "{order.totalAmount.min}")
private BigDecimal totalAmount;



}
