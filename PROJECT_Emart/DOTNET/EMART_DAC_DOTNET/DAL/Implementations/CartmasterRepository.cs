using EMART_DAC.Models;
using EMART_DAC.DAL.Interfaces;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.DAL.Implementations
{
    public class CartmasterRepository : ICartmasterRepository
    {
        private readonly MyDbContext context;

        public CartmasterRepository(MyDbContext context)
        {
            this.context = context;
        }

        public async Task<ActionResult<Cartmaster>> Add(Cartmaster cart)
        {
            context.Cartmasters.Add(cart);
            await context.SaveChangesAsync();
            return cart;
        }

        public async Task<Cartmaster?> Delete(int CartId)
        {
            Cartmaster? cart = await context.Cartmasters.FindAsync(CartId);
            if (cart != null)
            {
                context.Cartmasters.Remove(cart);
                await context.SaveChangesAsync();
            }
            return cart;
        }

        public async Task<ActionResult<IEnumerable<Cartmaster>>?> GetAllCart()
        {
            if (context.Cartmasters == null)
            {
                return null;
            }
            return await context.Cartmasters.ToListAsync();
        }

        public async Task<ActionResult<Cartmaster>?> GetCart(int CartId)
        {
            if (context.Cartmasters == null)
            {
                return null;
            }
            var cart = await context.Cartmasters.FindAsync(CartId);

            if (cart == null)
            {
                return null;
            }

            return cart;
        }

        public async Task<Cartmaster?> Update(int CartId, Cartmaster cartChanges)
        {
            if (CartId != cartChanges.CartId)
            {
                return null;
            }

            context.Entry(cartChanges).State = EntityState.Modified;

            try
            {
                await context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!cartExists(CartId))
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
        private bool cartExists(int CartId)
        {
            return (context.Cartmasters?.Any(e => e.CartId == CartId)).GetValueOrDefault();
        }

        public async Task<List<Cartmaster>> FindProdByCustID(int custID)
        {
            return await context.Cartmasters
                                .Where(c => c.Custid == custID)
                                .ToListAsync();
        }

        public async Task UpdateQty(int newQty, int cartId)
        {
            var cartItem = await context.Cartmasters.FirstOrDefaultAsync(c => c.CartId == cartId);
            if (cartItem != null)
            {
                cartItem.Qty = newQty;
                await context.SaveChangesAsync();
            }
        }

        public async Task DeletecartByCust(int cid)
        {
            var cartItems = await context.Cartmasters.Where(c => c.Custid == cid).ToListAsync();
            context.Cartmasters.RemoveRange(cartItems);
            await context.SaveChangesAsync();
        }

    }
}
