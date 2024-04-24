package com.example.repositeries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.InvoiceMaster;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface InvoiceMasterRepository extends JpaRepository<InvoiceMaster, Integer> {
	@Query(value = "SELECT * FROM InvoiceMaster WHERE custid = :custid ORDER BY InvoiceDate DESC LIMIT 1", nativeQuery = true)
	InvoiceMaster getMostRecentInvoiceByCustomerId(@Param("custid") int custid);


}
