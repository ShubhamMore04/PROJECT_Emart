using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Controllers
{
    [Route("/invoice")]
    [ApiController]

    public class InvoiceMasterController : ControllerBase
    {
        private readonly IInvoiceMasterRepository _repository;

        public InvoiceMasterController(IInvoiceMasterRepository repository)
        {
            _repository = repository;

        }
        [HttpGet]
        public async Task<ActionResult<IEnumerable<InvoiceMaster>>?> GetInvoicemasters()
        {
            if (await _repository.GetAllInvoice() == null)
            {
                return NotFound();
            }

            return await _repository.GetAllInvoice();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<InvoiceMaster>> GetInvoiceById(int id)
        {
            var invoicemaster = await _repository.GetInvoice(id);
            return invoicemaster == null ? NotFound() : invoicemaster;
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> PutInvoice(int id, InvoiceMaster invoiceMaster)
        {
            if (id != invoiceMaster.InvoiceId)
            {
                return BadRequest();
            }
            try
            {
                await _repository.Update(id, invoiceMaster);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (_repository.GetInvoice(id) == null)
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
        public async Task<ActionResult<InvoiceMaster>> PostInvoice(InvoiceMaster invoiceMaster)
        {
            await _repository.Add(invoiceMaster);
            return CreatedAtAction(nameof(GetInvoiceById), new { id = invoiceMaster.InvoiceId }, invoiceMaster);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteInvoice(int id)
        {
            if (_repository.GetAllInvoice() == null)
            {
                return NotFound();
            }
            var Customermaster = await _repository.GetInvoice(id);
            if (Customermaster == null)
            {
                return NotFound();
            }
            await _repository.Delete(id);
            return NoContent();
        }
    }
}
