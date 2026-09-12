package com.example.customer.service.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.customer.service.dto.PaginationDto;
import com.example.customer.service.dto.PaginationResponseDto;
import com.example.customer.service.model.Customer;

import jakarta.validation.Valid;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer, Long>{
	
	@Query("select count(c)>0 from Customer c where c.email = :email")
	boolean emailAlreadyExist(@Param("email") String email);

	Optional<Customer> findByPhoneNumber(Long phoneNumber);
	
	@Query("select c.customerId as customerId,c.name as name,c.email as email,c.phoneNumber as phoneNumber,c.address  as address from Customer c "
			+ " where c.email= :email and c.phoneNumber = :phoneNumber")
	Map<String, Object> findByEmailAndPhoneNumber(@Param("email") String email,@Param("phoneNumber") Long phoneNumber);



	@Query(value = """
	        SELECT
	            oi.product_name AS productName,
	            oi.quantity AS quantity,
	            oi.unit_price AS unitPrice,
	            o.status AS status,
	            o.total_amount AS totalOrdedAmount,
	            (oi.quantity * oi.unit_price) AS productPrice
	        FROM customer c
	        JOIN orders o
	            ON c.customer_id = o.customer_id
	        JOIN order_item oi
	            ON o.order_id = oi.order_id
	        WHERE (
	            :#{#paginationDto.email} IS NULL
	            OR :#{#paginationDto.email} = ''
	            OR c.email = :#{#paginationDto.email}
	        )
	        AND (
	            :#{#paginationDto.phoneNumber} IS NULL
	            OR c.phone_number = :#{#paginationDto.phoneNumber}
	        )
	        ORDER BY o.order_date
	        OFFSET :offset ROWS
	        FETCH NEXT :#{#paginationDto.size} ROWS ONLY
	        """,
	        nativeQuery = true)
	List<Map<String, Object>> getPageDetaills(
	        @Param("offset") int offset,
	        @Param("paginationDto") PaginationDto paginationDto);
	
	@Query(value = """
	        SELECT
	           COUNT(*) AS count
	        FROM customer c
	        JOIN orders o
	            ON c.customer_id = o.customer_id
	        JOIN order_item oi
	            ON o.order_id = oi.order_id
	        WHERE (
	            :#{#paginationDto.email} IS NULL
	            OR :#{#paginationDto.email} = ''
	            OR c.email = :#{#paginationDto.email}
	        )
	        AND (
	            :#{#paginationDto.phoneNumber} IS NULL
	            OR c.phone_number = :#{#paginationDto.phoneNumber}
	        )
	       
	        """,
	        nativeQuery = true)
	Long count(@Param("paginationDto") PaginationDto paginationDto);
	
	

	@Query("""
			SELECT new map ( oi.productName AS productName,
	            oi.quantity AS quantity,
	            oi.unitPrice AS unitPrice,
	            o.status AS status,
	            o.totalAmount AS totalOrdedAmount,
	            (oi.quantity * oi.unitPrice) AS productPrice) from Customer c
			 JOIN c.orders o 
			 JOIN o.orderItems oi
          WHERE (
	            :#{#paginationDto.email} IS NULL
	            OR :#{#paginationDto.email} = ''
	            OR c.email = :#{#paginationDto.email}
	        )
	        AND (
	            :#{#paginationDto.phoneNumber} IS NULL
	            OR c.phoneNumber = :#{#paginationDto.phoneNumber}
	        ) order By o.orderDate
	       			""")
	
	Page<Map<String, Object>> getRecordsByUsinJpaQueries(@Param("paginationDto") PaginationDto paginationDto, Pageable pageable);

	@Query("""
			SELECT new com.example.customer.service.dto.PaginationResponseDto ( oi.productName AS productName,
	            oi.quantity AS quantity,
	            oi.unitPrice AS unitPrice,
	            o.status AS status,
	            o.totalAmount AS totalOrdedAmount,
	            (oi.quantity * oi.unitPrice) AS productPrice) from Customer c
			 JOIN c.orders o 
			 JOIN o.orderItems oi
          WHERE (
	            :#{#paginationDto.email} IS NULL
	            OR :#{#paginationDto.email} = ''
	            OR c.email = :#{#paginationDto.email}
	        )
	        AND (
	            :#{#paginationDto.phoneNumber} IS NULL
	            OR c.phoneNumber = :#{#paginationDto.phoneNumber}
	        ) order By o.orderDate
	       			""")
	Page<PaginationResponseDto> getRecordsByUsinJpaQueriesDto(@Valid @Param("paginationDto") PaginationDto paginationDto, Pageable pageable);
	
}
