using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.DAL.Implementations
{
    public class CategoryMasterRepository : ICategoryMasterRepository
    {

        private readonly MyDbContext context;

        public CategoryMasterRepository(MyDbContext context)
        {
            this.context = context;
        }

        public async Task<ActionResult<CategoryMaster>> Add(CategoryMaster category)
        {
            context.CategoryMasters.Add(category);
            await context.SaveChangesAsync();
            return category;

        }

        public async Task<CategoryMaster?> Delete(int CatmasterId)
        {
            CategoryMaster? category = await context.CategoryMasters.FindAsync(CatmasterId);
            if (category == null)
            {
                return null;
            }
            context.CategoryMasters.Remove(category);
            await context.SaveChangesAsync();

            return category;
        }



        public async Task<ActionResult<IEnumerable<CategoryMaster>>?> GetAllCategoryMaster()
        {

            if (context.CategoryMasters == null)
            {
                return null;
            }
            return await context.CategoryMasters.ToListAsync();

        }



        public async Task<ActionResult<CategoryMaster>?> GetCategoryMaster(int CatmasterId)
        {
            if (context.CategoryMasters == null)

            {
                return null;
            }
            var category = await context.CategoryMasters.FindAsync(CatmasterId);

            if (category == null)
            {
                return null;
            }

            return category;

        }

        public async Task<CategoryMaster?> Update(int id, CategoryMaster categorychanges)
        {
            if (id != categorychanges.CatmasterId)
            {
                return null;
            }

            context.Entry(categorychanges).State = EntityState.Modified;

            try
            {
                await context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CategoryMasterExists(id))
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
        private bool CategoryMasterExists(int id)
        {
            return (context.CategoryMasters?.Any(e => e.CatmasterId == id)).GetValueOrDefault();
        }

        public List<CategoryMaster> FindByCategoryName(string categoryName)
        {
            try
            {
                var categories = context.CategoryMasters
                                        .Where(c => c.CategoryName == categoryName)
                                        .ToList();
                return categories;
            }
            catch (Exception)
            {
                throw;
            }
        }

        public List<CategoryMaster> FindByParentCatID(int parentCatID)
        {
            try
            {
                var categories = context.CategoryMasters
                                        .Where(c => c.ParentCatid == parentCatID)
                                        .ToList();
                return categories;
            }
            catch (Exception)
            {
                throw;
            }
        }


    }

}