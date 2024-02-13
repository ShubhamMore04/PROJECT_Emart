package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Invoice_dtl;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface Invoice_dtlRepository extends JpaRepository<Invoice_dtl, Integer> {
    
    @Query(value = "SELECT d FROM Invoice_dtl d WHERE d.invid = :invid")
    Invoice_dtl getInvoice_dtlIdByInvoiceId(@Param("invid") int invid);
}