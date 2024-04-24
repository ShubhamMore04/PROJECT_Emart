using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using EMART_DAC.Models.DTO;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Controllers
{
    [Route("/Customer")]
    [ApiController]
    public class CustomermasterController : ControllerBase
    {
        private readonly ICustomermasterRepository _repository;

        public CustomermasterController(ICustomermasterRepository repository)
        {
            _repository = repository;

        }
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Customermaster>>?> GetCustomermasters()
        {
            if (await _repository.GetAllCustomers() == null)
            {
                return NotFound();
            }

            return await _repository.GetAllCustomers();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Customermaster>> GetCustomerById(int id)
        {
            var Customermaster = await _repository.GetCustomer(id);
            return Customermaster == null ? NotFound() : Customermaster;
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> PutCustomer(int id, Customermaster Customermaster)
        {
            if (id != Customermaster.CustId)
            {
                return BadRequest();
            }
            try
            {
                await _repository.Update(id, Customermaster);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (_repository.GetCustomer(id) == null)
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
        public async Task<ActionResult<Customermaster>> PostCustomer(Customermaster Customermaster)
        {
            await _repository.Add(Customermaster);
            return CreatedAtAction(nameof(GetCustomerById), new { id = Customermaster.CustId }, Customermaster);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCustomer(int id)
        {
            if (_repository.GetAllCustomers() == null)
            {
                return NotFound();
            }
            var Customermaster = await _repository.GetCustomer(id);
            if (Customermaster == null)
            {
                return NotFound();
            }
            await _repository.Delete(id);
            return NoContent();
        }
        [HttpGet("ByEmail/{custEmail}")]
        public async Task<ActionResult<Customermaster>> GetCustomerByEmail(string custEmail)
        {
            try
            {
                var customer = await _repository.GetCustomerByEmail(custEmail);

                if (customer == null)
                    return NotFound();

                return customer;
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpGet("byId/{customerId}")]
        public async Task<ActionResult<string>> FindEmailById(int customerId)
        {
            try
            {
                var email = await _repository.FindEmailById(customerId);

                if (email == null)
                    return NotFound();

                return email;
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpGet("prime")]
        public async Task<ActionResult<Customermaster>> FindByCardHolderTrue()
        {
            try
            {
                var customer = await _repository.FindByCardHolderTrue();

                if (customer == null)
                    return NotFound();

                return customer;
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpPost("login")]
        public async Task<ActionResult<int>> CheckCust(Authentication a)
        {
            try
            {
                var custId = await _repository.CheckCust(a.CustEmail, a.CustPassword);

                if (custId == 0)
                    return NotFound();

                return custId;
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpGet("isCardHolder/{cid}")]
        public async Task<ActionResult<bool>> CheckCardHolder(int cid)
        {
            try
            {
                var cardHolder = await _repository.CheckCardHolder(cid);

        

                return cardHolder;
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpGet("points/{cid}")]
        public async Task<ActionResult<int>> GetPointsByID(int cid)
        {
            try
            {
                var points = await _repository.GetPointsByID(cid);

               

                return points;
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpPut("points/{cid}/{newPoints}")]
        public async Task<ActionResult<bool>> PutPointsByID(int cid, int newPoints)
        {
            try
            {
                var points = await _repository.PutPointsByID(cid, newPoints);
                return points;
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }
    }
}

