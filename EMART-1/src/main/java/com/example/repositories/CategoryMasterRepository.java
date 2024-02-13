package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.CategoryMaster;

@Repository
public interface CategoryMasterRepository extends JpaRepository<CategoryMaster, Integer> {
}