using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.DAL.Interfaces
{
    public interface IProductMasterRepository
    {
        Task<ActionResult<ProductMaster>?> GetProductMaster(int ProdId);
        Task<ActionResult<IEnumerable<ProductMaster>>?> GetAllProductMaster();
        Task<ActionResult<ProductMaster>> Add(ProductMaster productMaster);
        Task<ProductMaster?> Update(int prodid, ProductMaster productMaster);
        Task<ProductMaster?> Delete(int ProdId);
        Task<ActionResult<List<ProductMaster>>> FindProductsByMrpPriceBetween(double minPrice, double maxPrice);
        Task<ActionResult<List<ProductMaster>>> FindProductsWithValidDiscount();
        Task<ActionResult<List<ProductMaster>>> FindProductsByCatID(int catId);
        Task<ActionResult<IEnumerable<ProductMaster>>?> GetProductsMaxDiscount();

    }
}
