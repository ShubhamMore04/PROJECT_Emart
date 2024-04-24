using EMART_DAC.Models;
using System.Drawing.Printing;
using System.Drawing;
using System.Xml.Linq;
using System.Collections.Generic;
using System.IO;
using System.Drawing;
using iTextSharp.text;
using iTextSharp.text.pdf;
using Font = iTextSharp.text.Font;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.Controllers
{
    public class ProductPDFExporter
    {
        private static readonly string DIRECTORY_PATH = @"E:\VITA\Project\DotNet\EMART_DAC\pdfs";
        private static readonly string FILE_NAME = "exported.pdf";

        private List<InvoiceMaster> productList;

        public ProductPDFExporter(List<InvoiceMaster> products)
        {
            this.productList = products;
        }

        public void SetProducts(List<InvoiceMaster> products)
        {
            this.productList = products;
        }

        public string ExportToPdfFile()
        {
            string filePath = System.IO.Path.Combine(DIRECTORY_PATH, FILE_NAME);

            using (FileStream fs = new FileStream(filePath, FileMode.Create))
            {
                ExportToPdf(fs);
            }
            return filePath;
        }

        private void ExportToPdf(FileStream fs)
        {
            Document document = new Document(PageSize.A4);
            PdfWriter.GetInstance(document, fs);

            document.Open();

            // Apply styling to the title
            Font titleFont = FontFactory.GetFont(FontFactory.HELVETICA_BOLD);
            titleFont.Size = 18;
            titleFont.Color = BaseColor.BLUE;

            Paragraph title = new Paragraph("Invoice Details", titleFont);
            title.Alignment = Element.ALIGN_CENTER;
            document.Add(title);

            // Apply styling to the table
            PdfPTable table = new PdfPTable(7);
            table.WidthPercentage = 100f;
            table.SetWidths(new float[] { 1, 1, 1, 1, 1, 1, 1 }); // Equal column widths

            // Set default cell properties
            table.DefaultCell.HorizontalAlignment = Element.ALIGN_CENTER;
            table.DefaultCell.VerticalAlignment = Element.ALIGN_MIDDLE;

            // Apply styling to the table header
            WriteTableHeader(table);

            // Apply styling to the table data
            WriteTableData(table);

            document.Add(table);
            document.Close();
        }

        private void WriteTableHeader(PdfPTable table)
        {
            Font font = FontFactory.GetFont(FontFactory.HELVETICA_BOLD);
            font.Color = BaseColor.WHITE;

            string[] headers = { "InvoiceID", "CustID", "Tax", "TotalAmt", "TotalBill", "DeliveryCharge", "InvoiceDate" };

            foreach (string header in headers)
            {
                PdfPCell cell = CreateCell(header, font, BaseColor.GRAY);
                table.AddCell(cell);
            }
        }

        private void WriteTableData(PdfPTable table)
        {
            Font font = FontFactory.GetFont(FontFactory.HELVETICA); // Change font for data

            foreach (InvoiceMaster product in productList)
            {
                table.AddCell(CreateCell(product.InvoiceId.ToString(), font, BaseColor.WHITE));
                table.AddCell(CreateCell(product.Custid.ToString(), font, BaseColor.WHITE));
                table.AddCell(CreateCell(product.Tax.ToString(), font, BaseColor.WHITE));
                table.AddCell(CreateCell(product.TotalAmt.ToString(), font, BaseColor.WHITE));
                table.AddCell(CreateCell(product.TotalBill.ToString(), font, BaseColor.WHITE));
                table.AddCell(CreateCell(product.DeliveryCharge.ToString(), font, BaseColor.WHITE));
                table.AddCell(CreateCell(product.InvoiceDate.ToString(), font, BaseColor.WHITE));
            }
        }

        private PdfPCell CreateCell(string text, Font font, BaseColor backgroundColor)
        {
            PdfPCell cell = new PdfPCell(new Phrase(text, font));
            cell.Padding = 8;
            cell.BackgroundColor = backgroundColor;
            return cell;
        }
    }
}
