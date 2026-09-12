package com.example.customer.service.controller;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.service.dto.CustomerRequestDto;
import com.example.customer.service.dto.CustomerResponseDto;
import com.example.customer.service.dto.CustomerUpdateDto;
import com.example.customer.service.dto.PaginationDto;
import com.example.customer.service.dto.PaginationResponseDto;
import com.example.customer.service.dto.ResponseDto;
import com.example.customer.service.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
@AllArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	private final MessageSource messageSource;
	
	@PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
		
		CustomerResponseDto customer=customerService.createCustomer(customerRequestDto);
		
         ResponseDto<Object> dto=ResponseDto.builder()
					                 .statusCode(HttpStatus.CREATED.value())
					                 .success(Boolean.TRUE)
					                 .timesTamp(LocalDateTime.now())
					                 .mesaage(messageSource.getMessage("customer.successfully.created", null, Locale.getDefault()))
					                 .data(customer)
					                  
					                 .build();
			
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
			
			
			
		
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getAll(@PathVariable("id") Long id){
		CustomerResponseDto customer=customerService.getAllCustomer(id);
		  ResponseDto<Object> dto=ResponseDto.builder()
	                 .statusCode(HttpStatus.CREATED.value())
	                 .success(Boolean.TRUE)
	                 .timesTamp(LocalDateTime.now())
	                 .mesaage(messageSource.getMessage("customer.successfully.retrive", null, Locale.getDefault()))
	                 .data(customer)
	                  
	                 .build();

return ResponseEntity.status(HttpStatus.OK).body(dto);

		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateCustomer(@Valid @RequestBody CustomerUpdateDto customerUpdateDto){
		
		CustomerResponseDto customerResponseDto=customerService.updateCustomer(customerUpdateDto);
		ResponseDto<Object> dto=ResponseDto.builder()
				                         .statusCode(HttpStatus.OK.value())
				                         .success(Boolean.TRUE)
				                         .data(customerResponseDto)
				                         .mesaage(messageSource.getMessage("customer.successfully.updated",null,Locale.getDefault()))
				                         .timesTamp(LocalDateTime.now())
				                         .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
		
	}
	
	
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteCustomer(@RequestParam(required = false) Long customerId){
		 boolean flag =customerService.deleteCustomer(customerId);
		 if(flag) {
		ResponseDto<Object> dto=ResponseDto.builder().statusCode(HttpStatus.NO_CONTENT.value())
				                                      .success(Boolean.TRUE)
				                                      .mesaage("")
				                                      .data(null)
				                                      .timesTamp(LocalDateTime.now())
				                                      .build();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
				
		 }else {
			 ResponseDto<Object> dto=ResponseDto.builder()
					                            .statusCode(HttpStatus.BAD_REQUEST.value())
					                            .success(Boolean.FALSE)
					                            .mesaage("")
					                            .data(null)
					                            .timesTamp(LocalDateTime.now())
					                            .build();
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		 }
			 
		
	}
	
	//pagination  but using native (sql query) but it not most suggestable way becuase jpa there no offset and limit
	@PostMapping("/pagination")
	public ResponseEntity<Page<PaginationResponseDto>> getRecordsByUsingNativeQueries(@Valid @RequestBody PaginationDto paginationDto){
		Page<PaginationResponseDto> page=customerService.getRecords(paginationDto);
		return ResponseEntity.status(HttpStatus.OK).body(page);
		
	}
	
	
	//pagination  but using Jpa but it  most suggestable way and we need Page<Map<String , Object>>  is there any calculation after get data from db 

	@PostMapping("/pagination-jpa-map")
	public ResponseEntity<Page<PaginationResponseDto>> getRecordsByUsinJpaQueries(@Valid @RequestBody PaginationDto paginationDto){
		Page<PaginationResponseDto> page=customerService.getRecordsByUsinJpaQueries(paginationDto);
		return ResponseEntity.status(HttpStatus.OK).body(page);
		
	}
	
	//pagination  but using Jpa but it  most suggestable way and we need Page<PaginationResponseDto>  is there no calculation after get data from db 

	@PostMapping("/pagination-jpa-dto")
	public ResponseEntity<Page<PaginationResponseDto>> getRecordsByUsinJpaQueriesDto(@Valid @RequestBody PaginationDto paginationDto){
		Page<PaginationResponseDto> page=customerService.getRecordsByUsinJpaQueriesDto(paginationDto);
		return ResponseEntity.status(HttpStatus.OK).body(page);
		
	}
	
	
	
	
	

}
