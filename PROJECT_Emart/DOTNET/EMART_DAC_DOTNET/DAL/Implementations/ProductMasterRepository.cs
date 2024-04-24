using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.DAL.Implementations
{
    public class ProductMasterRepository : IProductMasterRepository
    {
        private readonly MyDbContext context;

        public ProductMasterRepository(MyDbContext context)
        {
            this.context = context;
        }

        public async Task<ActionResult<ProductMaster>> Add(ProductMaster productMaster)
        {
            context.ProductMasters.Add(productMaster);
            await context.SaveChangesAsync();
            return productMaster;
        }

        public async Task<ProductMaster?> Delete(int ProdId)
        {
            ProductMaster? productMaster = await context.ProductMasters.FindAsync(ProdId);
            if (productMaster != null)
            {
                context.ProductMasters.Remove(productMaster);
                await context.SaveChangesAsync();
            }
            return productMaster;
        }

        public async Task<ActionResult<IEnumerable<ProductMaster>>?> GetAllProductMaster()
        {
            if (context.ProductMasters == null)
            {
                return null;
            }
            return await context.ProductMasters.ToListAsync();

        }

        public async Task<ActionResult<ProductMaster>?> GetProductMaster(int ProdId)
        {
            if (context.ProductMasters == null)
            {
                return null;
            }
            var productMaster = await context.ProductMasters.FindAsync(ProdId);

            if (productMaster == null)
            {
                return null;
            }

            return productMaster;

        }

        public async Task<ProductMaster?> Update(int prodid, ProductMaster productMaster)
        {
            if (prodid != productMaster.ProdId)
            {
                return null;
            }

            context.Entry(productMaster).State = EntityState.Modified;

            try
            {
                await context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!productMasterExists(prodid))
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

        private bool productMasterExists(int prodid)
        {
            return (context.ProductMasters?.Any(e => e.ProdId == prodid)).GetValueOrDefault();
        }

        public async Task<ActionResult<List<ProductMaster>>> FindProductsByMrpPriceBetween(double minPrice, double maxPrice)
        {
            var products = await context.ProductMasters
                                   .Where(p => p.MrpPrice >= minPrice && p.MrpPrice <= maxPrice)
                                   .ToListAsync();

            return products;
        }

        public async Task<ActionResult<List<ProductMaster>>> FindProductsWithValidDiscount()
        {
            var allProducts = await context.ProductMasters.ToListAsync();
            var productsWithValidDiscount = allProducts
                .Where(product => product.OfferPrice < product.MrpPrice)
                .ToList();

            return productsWithValidDiscount;
        }

        public async Task<ActionResult<List<ProductMaster>>> FindProductsByCatID(int catId)
        {
            var products = await context.ProductMasters
                                   .Where(p => p.Catmasterid == catId)
                                   .ToListAsync();

            return products;
        }


        public async Task<ActionResult<IEnumerable<ProductMaster>>?> GetProductsMaxDiscount()
        {
            var topFourProducts = await context.ProductMasters.OrderByDescending(p => p.Disc).Take(4).ToListAsync();
            return topFourProducts;
        }


    }
}