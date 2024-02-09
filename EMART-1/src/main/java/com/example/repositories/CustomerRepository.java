package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Customer;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	
	@Query(value="SELECT * FROM Customer", nativeQuery = true)
	Optional<Customer> getCustomer();
}
