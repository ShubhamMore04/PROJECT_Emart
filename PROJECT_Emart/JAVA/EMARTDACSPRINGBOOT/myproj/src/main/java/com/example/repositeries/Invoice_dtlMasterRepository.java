package com.example.repositeries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.InvoiceDetailsMaster;

import jakarta.transaction.Transactional;
@Repository
@Transactional
public interface Invoice_dtlMasterRepository extends JpaRepository<InvoiceDetailsMaster, Integer> 
{
	
	@Query("SELECT i FROM InvoiceDetailsMaster i WHERE i.invoiceID = :inID")
    List<InvoiceDetailsMaster> findByInvID(@Param("inID") int inID);
}
