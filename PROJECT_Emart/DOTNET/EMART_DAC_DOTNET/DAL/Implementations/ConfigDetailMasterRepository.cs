using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.DAL.Implementations
{
    public class ConfigDetailMasterRepository : IConfigDetailMasterRepository
    {
        private readonly MyDbContext context;

        public ConfigDetailMasterRepository(MyDbContext context)
        {
            this.context = context;
        }


        public async Task<ActionResult<ConfigDetailMaster>> Add(ConfigDetailMaster config)
        {
            context.ConfigDetailMasters.Add(config);
            await context.SaveChangesAsync();
            return config;

        }

        public async Task<ConfigDetailMaster?> Delete(int Id)
        {
            ConfigDetailMaster? config = await context.ConfigDetailMasters.FindAsync(Id);
            if (config == null)
            {
                return null;
            }
            context.ConfigDetailMasters.Remove(config);
            await context.SaveChangesAsync();
            return config;

        }

        public async Task<ActionResult<IEnumerable<ConfigDetailMaster>>?> GetAllConfig()
        {
            if (context.ConfigDetailMasters == null)
            {
                return null;
            }
            return await context.ConfigDetailMasters.ToListAsync();


        }

        public async Task<ActionResult<ConfigDetailMaster>?> GetConfig(int Id)
        {
            if (context.ConfigDetailMasters == null)
            {
                return null;
            }
            var config = await context.ConfigDetailMasters.FindAsync(Id);

            if (config == null)
            {
                return null;
            }

            return config;

        }


        public async Task<ConfigDetailMaster?> Update(int id, ConfigDetailMaster configChanges)
        {
            if (id != configChanges.Configid)
            {
                return null;
            }

            context.Entry(configChanges).State = EntityState.Modified;

            try
            {
                await context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ConfigDetailMasterExists(id))
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

        private bool ConfigDetailMasterExists(int id)
        {
            return (context.ConfigDetailMasters?.Any(e => e.ConfigDetailsid == id)).GetValueOrDefault();
        }
    }

}