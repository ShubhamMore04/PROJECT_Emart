package com.example.repositeries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.OrderMaster;

public interface OrderMasterRepository  extends JpaRepository <OrderMaster,Integer>{
	
}
