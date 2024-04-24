using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models.DTO;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.Controllers
{

    [ApiController]
    public class EmailController : ControllerBase
    {
        private readonly IEmailService _emailService;
        private readonly IInvoiceMasterRepository _invoiceMasterRepository;
        private readonly ProductPDFExporter _productPDFExporter;

        public EmailController(IEmailService emailService, IInvoiceMasterRepository invoiceMasterService, ProductPDFExporter productPDFExporter)
        {
            _emailService = emailService;
            _invoiceMasterRepository = invoiceMasterService;
            _productPDFExporter = productPDFExporter;
        }

        [HttpPost("sendMailWithPDFAttachment")]
        public async Task<IActionResult> SendMailWithPDFAttachment([FromBody] EmailMaster details)
        {
            try
            {

                var productList = await RetrieveInvoiceData(); // Convert IEnumerable to List
                //_productPDFExporter.SetProducts(productList);    // Provide the required dependency
                var pdfFilePath = _productPDFExporter.ExportToPdfFile();
                details.Attachment = pdfFilePath;
                var status = await _emailService.SendMailWithAttachment(details);
                return Ok(status);
            }
            catch (Exception e)
            {
                // Handle the exception, e.g., log it
                return StatusCode(500, "Error exporting PDF or sending email");
            }
        }

        [HttpGet("details")]
        public async Task<ActionResult<IEnumerable<InvoiceMaster>>> RetrieveInvoiceData()
        {
            try
            {
                var invoiceData = await _invoiceMasterRepository.GetAllInvoice();
                return Ok(invoiceData);
            }
            catch (Exception ex)
            {
                return StatusCode(500, "Failed to retrieve invoice data");
            }
        }
    }
}