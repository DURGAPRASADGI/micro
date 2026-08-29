package com.example.customer.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.customer.service.model.Customer;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer, Long>{

}
