package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.EmailMaster;
import com.example.entities.InvoiceMaster;
import com.example.services.EmailMasterService;
import com.example.services.InvoiceMasterService;
@RestController
@CrossOrigin("*")
public class EmailMasterController {

    @Autowired
    private EmailMasterService emailService;

    @Autowired
    private ProductPDFExporter pdfExporter;

    @Autowired  // Add this annotation to autowire the InvoiceMasterService
    private InvoiceMasterService invoiceMasterService;
    @PostMapping("/sendMailWithPDFAttachment")
    public ResponseEntity<String> sendMailWithPDFAttachment(@RequestBody EmailMaster details) {
        try {
        	String recipientEmail = details.getRecipient();

        	 EmailMaster e = new EmailMaster(); // Instantiate EmailMaster
        	    e.setRecipient(recipientEmail);

        	e.setRecipient("details");
            // Retrieve the list of InvoiceMaster objects (adjust this based on your application)
            List<InvoiceMaster> products = retrieveInvoiceData();

            // Set the list of products in the PDF exporter
            pdfExporter.setProducts(products);

            // Export PDF to file
            String pdfFilePath = pdfExporter.exportToPdfFile();

            // Attach the exported PDF to the email
            details.setAttachment(pdfFilePath);

            // Send email with attachment
            String status = emailService.sendMailWithAttachment(details);

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            // Handle the exception, e.g., log it
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error exporting PDF or sending email");
        }
    }

    // Replace this method with your actual logic to retrieve invoice data
    private List<InvoiceMaster> retrieveInvoiceData() {
        // Implement your logic to retrieve InvoiceMaster data
        // For example, you can use the InvoiceMasterService to fetch data from the database
        return invoiceMasterService.listAll();
    }
}
