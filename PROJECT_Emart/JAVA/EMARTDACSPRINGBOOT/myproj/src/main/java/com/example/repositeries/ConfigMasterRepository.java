package com.example.repositeries;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.ConfigMaster;

import jakarta.transaction.Transactional;
@Repository
@Transactional
public interface ConfigMasterRepository extends JpaRepository<ConfigMaster, Integer> 
{
	
}
