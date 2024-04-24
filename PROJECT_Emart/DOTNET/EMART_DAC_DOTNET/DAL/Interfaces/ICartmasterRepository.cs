
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.DAL.Interfaces
{
    public interface ICartmasterRepository
    {
        Task<ActionResult<Cartmaster>?> GetCart(int CartId);
        Task<ActionResult<IEnumerable<Cartmaster>>?> GetAllCart();
        Task<ActionResult<Cartmaster>> Add(Cartmaster cart);
        Task<Cartmaster?> Update(int CartId, Cartmaster cartChanges);
        Task<Cartmaster?> Delete(int CartId);
        Task<List<Cartmaster>> FindProdByCustID(int custID);
        Task UpdateQty(int newQty, int cartId);
        Task DeletecartByCust(int cid);
    }
}
