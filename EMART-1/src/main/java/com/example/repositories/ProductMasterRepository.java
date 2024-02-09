package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ProductMaster;


public interface ProductMasterRepository extends JpaRepository<ProductMaster, Integer>{
	
	
}
