package com.example.customer.service.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.customer.service.constant.CustomerConstant;
import com.example.customer.service.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExcepation extends ResponseEntityExceptionHandler{

	@Override
	protected  ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		
		  List<String> error= ex.getBindingResult().getFieldErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList());
		
	Map<String,String> err=	ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(e->e.getField(), e->e.getDefaultMessage()));
		
		ResponseDto<Object> dto=ResponseDto.builder()
				                           .statusCode(status.value())
				                           .success(Boolean.FALSE)
				                           .timesTamp(LocalDateTime.now())
				                           .mesaage(CustomerConstant.VALIDATION_FAILED)
				                           .data(error)
				                           .build();
				                    
		return ResponseEntity.status(status).body(dto);
	}


	@ExceptionHandler(exception = ResuorceAlreadyExist.class)
	public final  ResponseEntity<Object> ResourceAlreadyhandleException(Exception ex, WebRequest request)  {
		ResponseDto<Object> dto=ResponseDto.builder()
				                          .statusCode(HttpStatus.BAD_REQUEST.value())
				                          .success(Boolean.FALSE)
				                          .timesTamp(LocalDateTime.now())
				                          .mesaage(ex.getMessage())
				                          .data(null)
				                          .build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		
	}


	@ExceptionHandler(exception = Exception.class)
	public final  ResponseEntity<Object> exception(Exception ex, WebRequest request) throws Exception {
		ResponseDto<Object> dto=ResponseDto.builder()
				                          .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				                          .success(Boolean.FALSE)
				                          .timesTamp(LocalDateTime.now())
				                          .mesaage(ex.getMessage())
				                          .data(null)
				                          .build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
		
	}

}
