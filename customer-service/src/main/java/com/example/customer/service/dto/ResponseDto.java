package com.example.customer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {
	private int statusCode;
	private boolean success;
	private String mesaage;
	private T data;

}
