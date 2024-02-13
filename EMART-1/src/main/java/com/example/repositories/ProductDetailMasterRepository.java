package com.example.repositories;

import com.example.entities.ProductDetailMaster;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductDetailMasterRepository extends JpaRepository<ProductDetailMaster, Integer> {
    
	
}