package com.example.controllers;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.example.entities.InvoiceMaster;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
@Controller
public class ProductPDFExporter {

    private static final String DIRECTORY_PATH = "E:\\Myfinalproj\\myproj\\pdfs";
    private static final String FILE_NAME = "exported.pdf";

    private List<InvoiceMaster> products;

    public ProductPDFExporter(List<InvoiceMaster> products) {
        this.products = products;
    }
    public void setProducts(List<InvoiceMaster> products) {
        this.products = products;
    }

    public String exportToPdfFile() throws DocumentException, IOException {
        String filePath = DIRECTORY_PATH + "/" + FILE_NAME;
        File pdfFile = new File(filePath);

        try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
            exportToPdf(fos);
        }

        return filePath;
    }
  
    private void exportToPdf(FileOutputStream fos) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, fos);

        document.open();

        // Apply styling to the title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        titleFont.setSize(18);
        titleFont.setColor(Color.BLUE);

        Paragraph title = new Paragraph("Invoice Details", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        // Apply styling to the table
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1, 1, 1, 1, 1, 1, 1}); // Equal column widths

        // Set default cell properties
        table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

        // Apply styling to the table header
        writeTableHeader(table);

        // Apply styling to the table data
        writeTableData(table);

        document.add(table);
        document.close();
    }

    private void writeTableHeader(PdfPTable table) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        String[] headers = {"InvoiceID", "CustID", "Tax", "TotalAmt", "TotalBill", "DeliveryCharge", "InvoiceDate"};

        for (String header : headers) {
            PdfPCell cell = createCell(header, font, Color.GRAY);
            table.addCell(cell);
        }
    }

    private void writeTableData(PdfPTable table) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA); // Change font for data

        for (InvoiceMaster product : products) {
            table.addCell(createCell(String.valueOf(product.getInvoiceID()), font, Color.WHITE));
            table.addCell(createCell(String.valueOf(product.getCustID()), font, Color.WHITE));
            table.addCell(createCell(String.valueOf(product.getTax()), font, Color.WHITE));
            table.addCell(createCell(String.valueOf(product.getTotalAmt()), font, Color.WHITE));
            table.addCell(createCell(String.valueOf(product.getTotalBill()), font, Color.WHITE));
            table.addCell(createCell(String.valueOf(product.getDeliveryCharge()), font, Color.WHITE));
            table.addCell(createCell(String.valueOf(product.getInvoiceDate()), font, Color.WHITE));
        }
    }

    private PdfPCell createCell(String text, Font font, Color backgroundColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(8);
        cell.setBackgroundColor(backgroundColor);
        return cell;
    }
}
