package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.InvoiceDetailsMaster;
import com.example.repositeries.Invoice_dtlMasterRepository;

@Service
public class Invoice_detailsServiceImpl implements Invoice_dtlMasterService
{
	@Autowired
	private Invoice_dtlMasterRepository indetailRepository;

	@Override
	public List<InvoiceDetailsMaster> getAllInvoice_detailss() {
		// TODO Auto-generated method stub
		return indetailRepository.findAll();
	}

	@Override
	public InvoiceDetailsMaster getInvoice_detailsById(int invoice_detailsId) {
		// TODO Auto-generated method stub
		return indetailRepository.findById(invoice_detailsId).get();
	}

	@Override
	public InvoiceDetailsMaster addInvoice_details(InvoiceDetailsMaster invoice_details) {
		// TODO Auto-generated method stub
		return indetailRepository.save(invoice_details);
	}

	@Override
	public InvoiceDetailsMaster updateInvoice_details(int invoice_detailsId, InvoiceDetailsMaster updatedInvoice_details) {
		
		 InvoiceDetailsMaster existingInvoiceDetails = indetailRepository.findById(invoice_detailsId).orElse(null);

	        if (existingInvoiceDetails != null) {
	            // Update the fields of the existing invoice details with the values from the updated invoice details
	            existingInvoiceDetails.setMrp(updatedInvoice_details.getMrp());
	            existingInvoiceDetails.setCardHolderPrice(updatedInvoice_details.getCardHolderPrice());
	            existingInvoiceDetails.setPointsRedeem(updatedInvoice_details.getPointsRedeem());
	            existingInvoiceDetails.setInvoiceID(updatedInvoice_details.getInvoiceID());
	            existingInvoiceDetails.setProdID(updatedInvoice_details.getProdID());

	            // Save the updated invoice details back to the database
	            return indetailRepository.save(existingInvoiceDetails);
	        }

	        return null;
	}

	@Override
	public void deleteInvoice_details(int invoice_detailsId) {
		InvoiceDetailsMaster c=indetailRepository.findById(invoice_detailsId).get();
		if(c!=null)
		{
			indetailRepository.delete(c);
		}
		
	}
	
	public List<InvoiceDetailsMaster> GetDetails (int invoiceID){
		return indetailRepository.findByInvID(invoiceID);
	}

}
