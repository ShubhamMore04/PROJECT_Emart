package com.example.services;

import java.util.List;

import com.example.entities.Order;

public interface OrderService {

		 Order saveOrder(Order order);
		 List<Order> getAllOrders();
		 Order getOrderById(Long id);
		 void deleteOrderById(Long id);

}