using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Controllers
{
    [Route("/Category")]
    [ApiController]
    public class CategoryMasterController : ControllerBase
    {

        private readonly ICategoryMasterRepository _repository;

        public CategoryMasterController(ICategoryMasterRepository repository)
        {
            _repository = repository;

        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<CategoryMaster>>?> GetCategories()
        {
            if (await _repository.GetAllCategoryMaster() == null)
            {
                return NotFound();
            }

            return await _repository.GetAllCategoryMaster();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<CategoryMaster>> GetCategoryMasterById(int id)
        {
            var category = await _repository.GetCategoryMaster(id);
            return category == null ? NotFound() : category;
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> PutCategory(int id, CategoryMaster categorychanges)
        {
            if (id != categorychanges.CatmasterId)
            {
                return BadRequest();
            }
            try
            {
                await _repository.Update(id, categorychanges);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (_repository.GetCategoryMaster(id) == null)
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
        public async Task<ActionResult<CategoryMaster>> PostCategoryMaster(CategoryMaster category)
        {
            await _repository.Add(category);
            return CreatedAtAction(nameof(GetCategoryMasterById), new { id = category.CatmasterId }, category);
        }


        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCategoryMaster(int id)
        {
            if (_repository.GetAllCategoryMaster() == null)
            {
                return NotFound();
            }
            var category = await _repository.GetCategoryMaster(id);
            if (category == null)
            {
                return NotFound();
            }
            await _repository.Delete(id);
            return NoContent();
        }

        [HttpGet("byName/{categoryName}")]
        public ActionResult<List<CategoryMaster>> GetByCategoryName(string categoryName)
        {
            var categories = _repository.FindByCategoryName(categoryName);
            if (categories == null || categories.Count == 0)
            {
                return NotFound();
            }
            return Ok(categories);
        }

        [HttpGet("getCatNameByParentId/{parentCatID}")]
        public ActionResult<List<CategoryMaster>> GetByParentCatID(int parentCatID)
        {
            var categories = _repository.FindByParentCatID(parentCatID);
            if (categories == null || categories.Count == 0)
            {
                return NotFound();
            }
            return Ok(categories);
        }
    }
}