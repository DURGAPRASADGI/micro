package com.example.customer.service.dto;

import java.time.LocalDateTime;

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
public class ResponseDto<T> {
	private int statusCode;
	private boolean success;
	private LocalDateTime timesTamp;
	private String mesaage;
	private T data;

}
