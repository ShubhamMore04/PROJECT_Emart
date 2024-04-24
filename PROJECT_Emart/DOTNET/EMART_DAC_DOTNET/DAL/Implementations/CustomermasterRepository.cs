using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;

namespace EMART_DAC.DAL.Implementations
{
    public class CustomermasterRepository : ICustomermasterRepository
    {
        private readonly MyDbContext context;

        public CustomermasterRepository(MyDbContext context)
        {
            this.context = context;
        }

        public async Task<ActionResult<Customermaster>> Add(Customermaster customer)
        {
            context.Customermasters.Add(customer);
            await context.SaveChangesAsync();
            return customer;
        }

        public async Task<Customermaster?> Delete(int CustId)
        {
            Customermaster? customer = await context.Customermasters.FindAsync(CustId);
            if (customer == null)
            {
                return null;
            }
            context.Customermasters.Remove(customer);
            await context.SaveChangesAsync();
            return customer;
        }


        public async Task<ActionResult<IEnumerable<Customermaster>>?> GetAllCustomers()
        {
            if (context.Customermasters == null)
            {
                return null;
            }
            return await context.Customermasters.ToListAsync();

        }

        public async Task<ActionResult<Customermaster>?> GetCustomer(int CustId)
        {
            if (context.Customermasters == null)
            {
                return null;
            }
            var customer = await context.Customermasters.FindAsync(CustId);

            if (customer == null)
            {
                return null;
            }

            return customer;
        }

        public async Task<Customermaster?> Update(int CustId, Customermaster customerChanges)
        {
            if (CustId != customerChanges.CustId)
            {
                return null;
            }

            context.Entry(customerChanges).State = EntityState.Modified;

            try
            {
                await context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CustomerExists(CustId))
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
        private bool CustomerExists(int CustId)
        {
            return (context.Customermasters?.Any(e => e.CustId == CustId)).GetValueOrDefault();
        }
        public async Task<ActionResult<Customermaster>?> GetCustomerByEmail(string custEmail)
        {
            try
            {
                var customer = await context.Customermasters
                                            .FirstOrDefaultAsync(c => c.CustEmail == custEmail);

                if (customer == null)
                    return null; 

                return customer;
            }
            catch (Exception)
            {
                throw;
            }
        }

        public async Task<ActionResult<string>?> FindEmailById(int customerId)
        {
            try
            {
                var email = await context.Customermasters
                                         .Where(c => c.CustId == customerId)
                                         .Select(c => c.CustEmail)
                                         .FirstOrDefaultAsync();

                if (email == null)
                    return null;

                return email;
            }
            catch (Exception)
            { 
                throw;
            }
        }

        public async Task<Customermaster?> FindByCardHolderTrue()
        {
            return await context.Customermasters
                                .Where(c => c.CardHolder)
                                .FirstOrDefaultAsync();
        }

        public async Task<int> CheckCust(string e, string p)
        {
            return await context.Customermasters
                                .Where(c => c.CustEmail == e && c.CustPassword == p)
                                .Select(c => c.CustId)
                                .FirstOrDefaultAsync();
        }

        public async Task<bool> CheckCardHolder(int cid)
        {
            return await context.Customermasters
                                .Where(c => c.CustId == cid)
                                .Select(c => c.CardHolder)
                                .FirstOrDefaultAsync();
        }

        public async Task<int> GetPointsByID(int cid)
        {
            return await context.Customermasters
                                .Where(c => c.CustId == cid)
                                .Select(c => c.Points)
                                .FirstOrDefaultAsync();
        }

        public async Task<bool> PutPointsByID(int cid, int newPoints)
        {
            var customer = await context.Customermasters
                                    .Where(c => c.CustId == cid)
                                    .FirstOrDefaultAsync();

            if (customer != null)
            {
                customer.Points = newPoints;

                // Save changes to the database
                await context.SaveChangesAsync();

                return true; // Successfully updated points
            }

            return false;
        }
    }
}
