package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entities.Order;
import com.example.repositories.OrderRepository;

@Component
public class OrderServiceImpl implements OrderService{
	@Autowired
    private OrderRepository orderRepository;

	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}
	@Override
	public List<Order> getAllOrders() {
	    List<Order> orders = orderRepository.findAllWithOrderItems();
	    orders.forEach(order -> order.getOrderItems().size()); // Initialize lazy collection
	    return orders;
	}


	@Override
	public Order getOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id).orElse(null);
	}


	@Override
	public void deleteOrderById(Long id) {
		Order o = orderRepository.findById(id).get();
		if(o!=null)
		{
			orderRepository.delete(o);
		}

	}

}