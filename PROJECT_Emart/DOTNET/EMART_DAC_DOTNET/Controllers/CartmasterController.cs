using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Controllers
{
    [Route("/Cart")]
    [ApiController]
    public class CartmasterController : ControllerBase
    {
        private readonly ICartmasterRepository _repository;

        public CartmasterController(ICartmasterRepository repository)
        {
            _repository = repository;

        }
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Cartmaster>>?> GetCarts()
        {
            if (await _repository.GetAllCart() == null)
            {
                return NotFound();
            }

            return await _repository.GetAllCart();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Cartmaster>> GetCartById(int id)
        {
            var Cartmaster = await _repository.GetCart(id);
            return Cartmaster == null ? NotFound() : Cartmaster;
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> PutCart(int id, Cartmaster cartmaster)
        {
            if (id != cartmaster.CartId)
            {
                return BadRequest();
            }
            try
            {
                await _repository.Update(id, cartmaster);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (_repository.GetCart(id) == null)
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
        public async Task<ActionResult<Customermaster>> PostCart(Cartmaster cartmaster)
        {
            await _repository.Add(cartmaster);
            return CreatedAtAction(nameof(GetCartById), new { id = cartmaster.CartId }, cartmaster);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCart(int id)
        {
            
            
            await _repository.Delete(id);
            return NoContent();
        }

        [HttpGet("cust/{custID}")]
        public async Task<ActionResult<List<Cartmaster>>> FindProdByCustID(int custID)
        {
            try
            {
                var cartItems = await _repository.FindProdByCustID(custID);
                if (cartItems == null)
                    return NotFound();
                return cartItems;
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpPut("{newQty}/{cartId}")]
        public async Task<IActionResult> UpdateQty(int newQty, int cartId)
        {
            try
            {
                await _repository.UpdateQty(newQty, cartId);
                return NoContent();
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpDelete("Deletecust/{cid}")]
        public async Task<IActionResult> DeletecartByCust(int cid)
        {
            try
            {
                await _repository.DeletecartByCust(cid);
                return NoContent();
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }
    }
}
