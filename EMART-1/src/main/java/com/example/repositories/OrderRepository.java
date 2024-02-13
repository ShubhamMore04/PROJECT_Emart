package com.example.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Order;

import jakarta.transaction.Transactional;
@Repository
@Transactional

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems")
	List<Order> findAllWithOrderItems();

}