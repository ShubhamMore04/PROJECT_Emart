package com.example.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.EmailMaster;
import com.example.entities.InvoiceMaster;
import com.example.services.InvoiceMasterService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/invoice")
public class InvoiceMasterController {
    
    @Autowired
    private InvoiceMasterService invoiceServ;
    
    @GetMapping("/products/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        List<InvoiceMaster> products = invoiceServ.listAll();

        ProductPDFExporter exporter = new ProductPDFExporter(products);
        try {
            String filePath = exporter.exportToPdfFile();
            sendPdfResponse(filePath, response);
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log them)
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void sendPdfResponse(String filePath, HttpServletResponse response) throws IOException {
        File pdfFile = new File(filePath);
        
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + pdfFile.getName();
        response.setHeader(headerKey, headerValue);

        try (InputStream in = new FileInputStream(pdfFile);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    @PostMapping
    public ResponseEntity<?> addInvoice(@RequestBody InvoiceMaster i) {
        return new ResponseEntity<>(invoiceServ.saveInvoice(i), HttpStatus.CREATED);
    }

    @PostMapping("/sendInvoice")
    public ResponseEntity<String> sendInvoice(@RequestBody EmailMaster details) {
        try {
            // Assuming details contain necessary information for the invoice
            return ResponseEntity.ok(invoiceServ.sendMailWithAttachment(details));
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log them)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending invoice");
        }
    }

    @GetMapping
    public ResponseEntity<List<InvoiceMaster>> getAllCart() {
        return new ResponseEntity<>(invoiceServ.getAllInvoice(), HttpStatus.OK);
    }

    @GetMapping("/{InvoiceId}")
    public ResponseEntity<InvoiceMaster> getInvoiceByID(@PathVariable int InvoiceId) {
        return new ResponseEntity<>(invoiceServ.getInvoiceById(InvoiceId), HttpStatus.OK);
    }

    @DeleteMapping("/{InvoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable int id) {
        invoiceServ.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{Invoiceid}")
	public ResponseEntity<?> editInvoice (@RequestBody InvoiceMaster i,@PathVariable int id){
		return new ResponseEntity<> (invoiceServ.updateInvoice(i, id),HttpStatus.CREATED);
	}
}
