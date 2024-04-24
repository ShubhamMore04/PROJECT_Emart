using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.DAL.Interfaces
{
    public interface IOrderMasterRepository
    {
        Task<ActionResult<OrderMaster>?> GetOrderMaster(int OrderId);
        Task<ActionResult<IEnumerable<OrderMaster>>?> GetAllOrderMaster();
        Task<ActionResult<OrderMaster>> Add(OrderMaster orderMaster);
        Task<OrderMaster?> Update(int orderid, OrderMaster orderMasterChanges);
        Task<OrderMaster?> Delete(int OrderId);
    }
}
