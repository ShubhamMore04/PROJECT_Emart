using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Controllers
{
    [Route("/Invoicedetails")]
    [ApiController]
    public class InvoiceDetailsMasterController : ControllerBase
    {


        private readonly IInvoiceDetailsMasterRepository _repository;

        public InvoiceDetailsMasterController(IInvoiceDetailsMasterRepository repository)
        {
            _repository = repository;

        }
        [HttpGet]
        public async Task<ActionResult<IEnumerable<InvoiceDetailsMaster>>?> GetInvoiceDetailsMaster()
        {
            if (await _repository.GetAllInvoiceDetailsMaster() == null)
            {
                return NotFound();
            }

            return await _repository.GetAllInvoiceDetailsMaster();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<InvoiceDetailsMaster>> GetInvoiceDetailsMasterById(int id)
        {
            var invoice = await _repository.GetInvoiceDetailsMaster(id);
            return invoice == null ? NotFound() : invoice;
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> PutInvoiceDetailsMaster(int id, InvoiceDetailsMaster invoice)
        {
            if (id != invoice.InvoiceDtId)
            {
                return BadRequest();
            }
            try
            {
                await _repository.Update(id, invoice);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (_repository.GetInvoiceDetailsMaster(id) == null)
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }
            return NoContent();


        }
        [HttpPost]
        public async Task<ActionResult<InvoiceDetailsMaster>> PostInvoiceDetailsMaster(InvoiceDetailsMaster invoice)
        {
            await _repository.Add(invoice);
            return CreatedAtAction(nameof(GetInvoiceDetailsMasterById), new { id = invoice.InvoiceDtId }, invoice);
        }


        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteInvoiceDetailsMaster(int id)
        {
            if (_repository.GetAllInvoiceDetailsMaster() == null)
            {
                return NotFound();
            }
            var category = await _repository.GetInvoiceDetailsMaster(id);
            if (category == null)
            {
                return NotFound();
            }
            await _repository.Delete(id);
            return NoContent();
        }

        [HttpGet("InvoiceID/{id}")]
        public async Task<IActionResult> GetInvoiceDetailsByInvoiceId(int id)
        {
            var invoices = await _repository.findByInvID(id);
            if (invoices == null)
            {
                return NotFound();
            }
            return Ok(invoices);
        }


    }
}
