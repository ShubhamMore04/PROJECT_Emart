using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.DAL.Implementations
{
    public class InvoiceDetailsMasterRepository : IInvoiceDetailsMasterRepository
    {

        private readonly MyDbContext context;

        public InvoiceDetailsMasterRepository(MyDbContext context)
        {
            this.context = context;
        }

        public async Task<ActionResult<InvoiceDetailsMaster>> Add(InvoiceDetailsMaster invoice)
        {
            context.InvoiceDetailsMasters.Add(invoice);
            await context.SaveChangesAsync();
            return invoice;
        }

        public async Task<InvoiceDetailsMaster?> Delete(int InvoiceDtId)
        {
            InvoiceDetailsMaster? invoice = await context.InvoiceDetailsMasters.FindAsync(InvoiceDtId);
            if (invoice == null)
            {
                return null;
            }

            context.InvoiceDetailsMasters.Remove(invoice);
            await context.SaveChangesAsync();

            return invoice;

        }

        public async Task<ActionResult<IEnumerable<InvoiceDetailsMaster>>?> GetAllInvoiceDetailsMaster()
        {

            if (context.InvoiceDetailsMasters == null)
            {
                return null;
            }
            return await context.InvoiceDetailsMasters.ToListAsync();

        }

        public async Task<ActionResult<InvoiceDetailsMaster>?> GetInvoiceDetailsMaster(int InvoiceDtId)
        {
            if (context.InvoiceDetailsMasters == null)
            {
                return null;
            }
            var invoice = await context.InvoiceDetailsMasters.FindAsync(InvoiceDtId);

            if (invoice == null)
            {
                return null;
            }

            return invoice;

        }

        public async Task<InvoiceDetailsMaster?> Update(int id, InvoiceDetailsMaster invoicechanges)
        {
            if (id != invoicechanges.InvoiceDtId)
            {
                return null;
            }

            context.Entry(invoicechanges).State = EntityState.Modified;

            try
            {
                await context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!InvoiceDetailsMasterExists(id))
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
        private bool InvoiceDetailsMasterExists(int id)
        {
            return (context.InvoiceDetailsMasters?.Any(e => e.InvoiceDtId == id)).GetValueOrDefault();
        }

        public async Task<List<InvoiceDetailsMaster>?> findByInvID(int inID)
        {
            try
            {
                var invoices = await context.InvoiceDetailsMasters
                                            .Where(i => i.Invoiceid == inID)
                                            .ToListAsync();

                if (invoices == null || !invoices.Any())
                    return null;

                return invoices;
            }
            catch (Exception)
            {
                throw;
            }
        }

    }

}
