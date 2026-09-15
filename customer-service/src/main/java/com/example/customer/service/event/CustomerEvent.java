package com.example.customer.service.event;

import java.time.LocalDateTime;

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
public class CustomerEvent {
	private Long customerId;
	private String name;
	private String email;
	private Long phoneNumber;
	private String address;
	private EventStaus eventType;      // CREATED, UPDATED, DELETED
    private LocalDateTime eventTimestamp;
}
