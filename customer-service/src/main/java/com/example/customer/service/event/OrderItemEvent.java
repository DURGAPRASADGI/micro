package com.example.customer.service.event;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemEvent {
	private Long orderEventId;
	private Long productId;
	private String productName;
	private Integer quantity;
	private Integer unitPrice;
	private BigDecimal producrPrice;

}
