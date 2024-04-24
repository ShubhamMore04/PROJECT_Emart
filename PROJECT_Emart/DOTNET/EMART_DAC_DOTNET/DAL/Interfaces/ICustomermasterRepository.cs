
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.DAL.Interfaces
{
    public interface ICustomermasterRepository
    {
        Task<ActionResult<Customermaster>?> GetCustomer(int CustId);
        Task<ActionResult<IEnumerable<Customermaster>>?> GetAllCustomers();
        Task<ActionResult<Customermaster>> Add(Customermaster customer);
        Task<Customermaster?> Update(int CustId, Customermaster customerChanges);
        Task<Customermaster?> Delete(int CustId);
        Task<ActionResult<Customermaster>?> GetCustomerByEmail(string custEmail);
        Task<ActionResult<string>?> FindEmailById(int customerId);
        Task<Customermaster?> FindByCardHolderTrue();
        Task<int> CheckCust(string e, string p);
        Task<bool> CheckCardHolder(int cid);
        Task<int> GetPointsByID(int cid);
        Task<bool> PutPointsByID(int cid, int newPoints);

    }
}
