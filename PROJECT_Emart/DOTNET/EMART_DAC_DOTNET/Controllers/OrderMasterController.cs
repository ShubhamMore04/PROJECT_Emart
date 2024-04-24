using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrderMasterController : ControllerBase
    {
        private readonly IOrderMasterRepository _repository;

        public OrderMasterController(IOrderMasterRepository repository)
        {
            _repository = repository;

        }
        [HttpGet]
        public async Task<ActionResult<IEnumerable<OrderMaster>>?> GetOrderMasters()
        {
            if (await _repository.GetAllOrderMaster() == null)
            {
                return NotFound();
            }

            return await _repository.GetAllOrderMaster();
        }

        [HttpGet("{orderid}")]
        public async Task<ActionResult<OrderMaster>> GetById_ActionResultOfT(int orderid)
        {
            var orderMaster = await _repository.GetOrderMaster(orderid);
            return orderMaster == null ? NotFound() : orderMaster;
        }

        [HttpPut("{orderid}")]
        public async Task<IActionResult> PutOrderMaster(int orderid, OrderMaster orderMaster)
        {
            if (orderid != orderMaster.OrderId)
            {
                return BadRequest();
            }
            try
            {
                await _repository.Update(orderid, orderMaster);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (_repository.GetOrderMaster(orderid) == null)
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
        public async Task<ActionResult<OrderMaster>> PostOrderMaster(OrderMaster oredrMaster)
        {
            await _repository.Add(oredrMaster);
            return CreatedAtAction(nameof(GetById_ActionResultOfT), new { orderid = oredrMaster.OrderId }, oredrMaster);
        }

        [HttpDelete("{orderid}")]
        public async Task<IActionResult> DeleteOrderMaster(int orderid)
        {
           
            
            await _repository.Delete(orderid);
            return NoContent();
        }
    }
}