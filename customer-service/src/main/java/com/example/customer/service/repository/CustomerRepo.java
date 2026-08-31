package com.example.customer.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.customer.service.model.Customer;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer, Long>{
	
	@Query("select count(c)>0 from Customer c where c.email = :email")
	boolean emailAlreadyExist(@Param("email") String email);

	Optional<Customer> findByPhoneNumber(Long phoneNumber);

}
