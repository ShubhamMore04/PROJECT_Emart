using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Controllers
{
    [Route("api/[controller]")]
    [ApiController]

    public class ProductMasterController : ControllerBase
    {
        private readonly IProductMasterRepository _repository;

        public ProductMasterController(IProductMasterRepository repository)
        {
            _repository = repository;

        }
        [HttpGet]
        public async Task<ActionResult<IEnumerable<ProductMaster>>?> GetProductMasters()
        {
            if (await _repository.GetAllProductMaster() == null)
            {
                return NotFound();
            }

            return await _repository.GetAllProductMaster();
        }

        [HttpGet("{prodid}")]
        public async Task<ActionResult<ProductMaster>> GetById_ActionResultOfT(int prodid)
        {
            var productMaster = await _repository.GetProductMaster(prodid);
            return productMaster == null ? NotFound() : productMaster;
        }

        [HttpPut("{prodid}")]
        public async Task<IActionResult> PutProductMaster(int prodid, ProductMaster productMaster)
        {
            if (prodid != productMaster.ProdId)
            {
                return BadRequest();
            }
            try
            {
                await _repository.Update(prodid, productMaster);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (_repository.GetProductMaster(prodid) == null)
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
        public async Task<ActionResult<ProductMaster>> PostProductMaster(ProductMaster productMaster)
        {
            await _repository.Add(productMaster);
            return CreatedAtAction(nameof(GetById_ActionResultOfT), new { prodid = productMaster.ProdId }, productMaster);
        }

        [HttpDelete("{prodid}")]
        public async Task<IActionResult> DeleteProductMaster(int prodid)
        {
            if (_repository.GetAllProductMaster() == null)
            {
                return NotFound();
            }
            var productMaster = _repository.Delete(prodid);
            if (productMaster == null)
            {
                return NotFound();
            }
            await _repository.Delete(productMaster.Id);
            return NoContent();
        }

        [HttpGet("productsByMrpPriceBetween")]
        public async Task<ActionResult<List<ProductMaster>>> GetProductsByMrpPriceBetween(double minPrice, double maxPrice)
        {
            var products = await _repository.FindProductsByMrpPriceBetween(minPrice, maxPrice);

            if (products == null)
            {
                return NotFound();
            }

            return products;
        }

        [HttpGet("productsWithValidDiscount")]
        public async Task<ActionResult<List<ProductMaster>>> GetProductsWithValidDiscount()
        {
            var products = await _repository.FindProductsWithValidDiscount();

            if (products == null)
            {
                return NotFound();
            }

            return products;
        }

        [HttpGet("productsByCatID/{catId}")]
        public async Task<ActionResult<List<ProductMaster>>> GetProductsByCatID(int catId)
        {
            var products = await _repository.FindProductsByCatID(catId);

            if (products == null)
            {
                return NotFound();
            }

            return products;
        }
        [HttpGet("discounted")]
        public async Task<ActionResult<IEnumerable<ProductMaster>>?> GetProductMaxDiscount()
        {
            return await _repository.GetProductsMaxDiscount();
        }
    }
}