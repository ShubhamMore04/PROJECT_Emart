package com.example.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.entities.EmailMaster;
import com.example.entities.InvoiceMaster;
import com.example.repositeries.InvoiceMasterRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class InvoiceMasterServiceImpl implements InvoiceMasterService {

    @Autowired
    private InvoiceMasterRepository invoiceRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Insert
    public InvoiceMaster saveInvoice(InvoiceMaster obj) {
        return invoiceRepo.save(obj);
    }

    // SelectAll
    public List<InvoiceMaster> getAllInvoice() {
        return invoiceRepo.findAll();
    }

    // SelectById
    public InvoiceMaster getInvoiceById(int id) {
        return invoiceRepo.findById(id).orElse(null);
    }

    // Delete
    public void deleteInvoice(int id) {
        invoiceRepo.deleteById(id);
    }

    // Update
    public InvoiceMaster updateInvoice(InvoiceMaster i, int id) {
        InvoiceMaster oldi = invoiceRepo.findById(id).orElse(null);
        if (oldi != null) {
            oldi.setCustID(i.getCustID());
            oldi.setTotalAmt(i.getTotalAmt());
            oldi.setTax(i.getTax());
            oldi.setDeliveryCharge(i.getDeliveryCharge());
            oldi.setTotalAmt(i.getTotalAmt());

            return invoiceRepo.save(oldi);
        } else {
            return null;
        }
    }

    @Override
    public List<InvoiceMaster> listAll() {
        return invoiceRepo.findAll();
    }

    @Override
    public String sendMailWithAttachment(EmailMaster details) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject("Your Invoice");

            // Generate the PDF invoice
            byte[] pdfBytes = generateInvoicePDF(details);

            // Add the attachment
            mimeMessageHelper.addAttachment("Invoice.pdf", new ByteArrayResource(pdfBytes));

            // Send the email
            javaMailSender.send(mimeMessage);

            return "Email sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email";
        }
    }

    // Method to generate the PDF invoice
    private byte[] generateInvoicePDF(EmailMaster details) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Add content to the PDF
            document.add(new Paragraph("Invoice details: " + details));

            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
