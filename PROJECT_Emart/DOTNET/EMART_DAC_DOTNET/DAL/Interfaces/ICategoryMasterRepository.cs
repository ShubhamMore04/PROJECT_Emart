using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.DAL.Interfaces
{
    public interface ICategoryMasterRepository
    {
        Task<ActionResult<CategoryMaster>?> GetCategoryMaster(int CatmasterId);
        Task<ActionResult<IEnumerable<CategoryMaster>>?> GetAllCategoryMaster();
        Task<ActionResult<CategoryMaster>> Add(CategoryMaster category);
        Task<CategoryMaster?> Update(int id, CategoryMaster categorychanges);
        Task<CategoryMaster?> Delete(int CatmasterId);
        public List<CategoryMaster> FindByCategoryName(string categoryName);
        public List<CategoryMaster> FindByParentCatID(int parentCatID);


    }
}
