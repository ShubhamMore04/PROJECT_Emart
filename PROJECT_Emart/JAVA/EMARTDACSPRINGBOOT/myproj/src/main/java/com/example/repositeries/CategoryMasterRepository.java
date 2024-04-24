package com.example.repositeries;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.CategoryMaster;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CategoryMasterRepository extends JpaRepository<CategoryMaster, Integer> 
{
    List<CategoryMaster> findByCategoryName(String categoryName);
    
    @Query("SELECT c FROM CategoryMaster c WHERE c.parentCatID = :parentId")
    List<CategoryMaster> findByParentCatID(@Param("parentId") int parentCatID);


}
