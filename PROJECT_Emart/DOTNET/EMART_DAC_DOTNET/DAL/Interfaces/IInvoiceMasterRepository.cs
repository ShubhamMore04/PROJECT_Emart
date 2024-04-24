
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.DAL.Interfaces
{
    public interface IInvoiceMasterRepository
    {
        Task<ActionResult<InvoiceMaster>?> GetInvoice(int InvoiceId);
        Task<ActionResult<IEnumerable<InvoiceMaster>>?> GetAllInvoice();
        Task<ActionResult<InvoiceMaster>> Add(InvoiceMaster invoice);
        Task<InvoiceMaster?> Update(int InvoiceId, InvoiceMaster invoiceChanges);
        Task<InvoiceMaster?> Delete(int InvoiceId);

    }
}
