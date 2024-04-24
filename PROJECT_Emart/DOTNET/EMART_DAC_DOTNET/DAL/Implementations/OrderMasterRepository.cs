using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.DAL.Implementations
{
    public class OrderMasterRepository : IOrderMasterRepository
    {
        private readonly MyDbContext context;

        public OrderMasterRepository(MyDbContext context)
        {
            this.context = context;
        }

        public async Task<ActionResult<OrderMaster>> Add(OrderMaster orderMaster)
        {
            context.OrderMasters.Add(orderMaster);
            await context.SaveChangesAsync();
            return orderMaster;
        }

        public async Task<OrderMaster?> Delete(int OrderId)
        {
            OrderMaster? orderMaster = await context.OrderMasters.FindAsync(OrderId);
            if (orderMaster == null)
            {
                return null;
            }
            context.OrderMasters.Remove(orderMaster);
            await context.SaveChangesAsync();
            return orderMaster;
        }

        public async Task<ActionResult<IEnumerable<OrderMaster>>?> GetAllOrderMaster()
        {
            if (context.OrderMasters == null)
            {
                return null;
            }
            return await context.OrderMasters.ToListAsync();
        }

        public async Task<ActionResult<OrderMaster>?> GetOrderMaster(int OrderId)
        {
            if (context.OrderMasters == null)
            {
                return null;
            }
            var oredrMaster = await context.OrderMasters.FindAsync(OrderId);

            if (oredrMaster == null)
            {
                return null;
            }

            return oredrMaster;
        }

        public async Task<OrderMaster?> Update(int orderid, OrderMaster orderMaster)
        {
            if (orderid != orderMaster.OrderId)
            {
                return null;
            }

            context.Entry(orderMaster).State = EntityState.Modified;

            try
            {
                await context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!orderMasterExists(orderid))
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

        private bool orderMasterExists(int orderid)
        {
            return (context.OrderMasters?.Any(e => e.OrderId == orderid)).GetValueOrDefault();
        }
    }
}