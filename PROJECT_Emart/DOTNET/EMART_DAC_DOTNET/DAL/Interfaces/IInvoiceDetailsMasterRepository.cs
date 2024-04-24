using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.DAL.Interfaces
{
    public interface IInvoiceDetailsMasterRepository
    {
        Task<ActionResult<InvoiceDetailsMaster>?> GetInvoiceDetailsMaster(int InvoiceDtId);
        Task<ActionResult<IEnumerable<InvoiceDetailsMaster>>?> GetAllInvoiceDetailsMaster();
        Task<ActionResult<InvoiceDetailsMaster>> Add(InvoiceDetailsMaster invoice);
        Task<InvoiceDetailsMaster?> Update(int id, InvoiceDetailsMaster invoicechanges);

        Task<InvoiceDetailsMaster?> Delete(int InvoiceDtId);
        Task<List<InvoiceDetailsMaster>?> findByInvID(int inID);

    }
}
