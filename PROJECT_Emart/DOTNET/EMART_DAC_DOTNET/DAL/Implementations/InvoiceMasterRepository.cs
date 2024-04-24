using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.DAL.Implementations
{
    public class InvoiceMasterRepository : IInvoiceMasterRepository
    {
        private readonly MyDbContext context;

        public InvoiceMasterRepository(MyDbContext context)
        {
            this.context = context;
        }

        public async Task<ActionResult<InvoiceMaster>> Add(InvoiceMaster invoice)
        {
            context.InvoiceMasters.Add(invoice);
            await context.SaveChangesAsync();
            return invoice;
        }

        public async Task<InvoiceMaster?> Delete(int InvoiceId)
        {
            InvoiceMaster? invoice = await context.InvoiceMasters.FindAsync(InvoiceId);
            if (invoice != null)
            {
                context.InvoiceMasters.Remove(invoice);
                await context.SaveChangesAsync();
            }
            return invoice;
        }

        public async Task<ActionResult<IEnumerable<InvoiceMaster>>?> GetAllInvoice()
        {
            if (context.InvoiceMasters == null)
            {
                return null;
            }
            return await context.InvoiceMasters.ToListAsync();
        }

        public async Task<ActionResult<InvoiceMaster>?> GetInvoice(int InvoiceId)
        {
            if (context.InvoiceMasters == null)
            {
                return null;
            }
            var invoice = await context.InvoiceMasters.FindAsync(InvoiceId);

            if (invoice == null)
            {
                return null;
            }

            return invoice;
        }

        public async Task<InvoiceMaster?> Update(int InvoiceId, InvoiceMaster invoiceChanges)
        {
            if (InvoiceId != invoiceChanges.InvoiceId)
            {
                return null;
            }

            context.Entry(invoiceChanges).State = EntityState.Modified;

            try
            {
                await context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!invoiceExists(InvoiceId))
                {
                    return null;
                }
                else
                {
                    throw;
                }
            }
            return null;
        }
        private bool invoiceExists(int InvoiceId)
        {
            return (context.InvoiceMasters?.Any(e => e.InvoiceId == InvoiceId)).GetValueOrDefault();
        }
    }
}
