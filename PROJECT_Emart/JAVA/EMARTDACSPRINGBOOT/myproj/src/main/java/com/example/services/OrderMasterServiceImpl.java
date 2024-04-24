package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.OrderMaster;
import com.example.repositeries.OrderMasterRepository;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {

	@Autowired
	private OrderMasterRepository orderRepo;
	
	public OrderMaster saveOrder(OrderMaster o)
	{
		return orderRepo.save(o);
	}
	
	public OrderMaster getOrderById(int id)
	{
		return orderRepo.findById(id).get();
	}
	
	public List<OrderMaster>getAllOrders()
	{
		return orderRepo.findAll();
	}
	
	public void deleteOrder(int id)
	{
		OrderMaster o = orderRepo.findById(id).get();
		if(o!=null)
		{
			orderRepo.delete(o);
		}
	}
	
	public OrderMaster update(OrderMaster o,int id)
	{
		OrderMaster order = orderRepo.findById(id).get();
		order.setCustID(o.getCustID());
		order.setShippingAdd(o.getShippingAdd());
		order.setOrderDate(o.getOrderDate());
		order.setDeliverydate(o.getDeliverydate());
		order.setCustID(o.getCustID());
		order.setInvoiceID(o.getInvoiceID());
		return orderRepo.save(order);
		
		
	}
	
}
