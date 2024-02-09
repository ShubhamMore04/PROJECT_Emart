package com.example.repositories;

import com.example.entities.ConfigMaster;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ConfigMasterRepository extends JpaRepository<ConfigMaster, Integer> {

	List<ConfigMaster> findAll();
    
}

