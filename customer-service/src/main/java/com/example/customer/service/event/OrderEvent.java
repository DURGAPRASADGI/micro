package com.example.customer.service.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.customer.service.model.OrderStatus;

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
public class OrderEvent {
	private Long customerId;
	private Long orderId;
	private LocalDateTime orderDate;
	private OrderStatus status;
	private BigDecimal totalAmount;
	private List<OrderItemEvent> itemEvents;

}
