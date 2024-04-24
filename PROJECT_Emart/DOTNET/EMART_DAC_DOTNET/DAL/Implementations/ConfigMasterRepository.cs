using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.DAL.Implementations
{
    public class ConfigMasterRepository : IConfigMasterRepository
    {
        private readonly MyDbContext context;

        public ConfigMasterRepository(MyDbContext context)
        {
            this.context = context;
        }


        public async Task<ActionResult<ConfigMaster>> Add(ConfigMaster config)
        {
            context.ConfigMasters.Add(config);
            await context.SaveChangesAsync();
            return config;

        }

        public async Task<ConfigMaster?> Delete(int Id)
        {
            ConfigMaster? config = await context.ConfigMasters.FindAsync(Id);
            if (config == null)
            {
                return null;
            }
            context.ConfigMasters.Remove(config);
            await context.SaveChangesAsync();
            return config;

        }

        public async Task<ActionResult<IEnumerable<ConfigMaster>>?> GetAllConfigMaster()
        {
            if (context.ConfigMasters == null)
            {
                return null;
            }
            return await context.ConfigMasters.ToListAsync();

        }

        public async Task<ActionResult<ConfigMaster>?> GetConfigMaster(int Id)
        {
            if (context.ConfigMasters == null)
            {
                return null;
            }
            var config = await context.ConfigMasters.FindAsync(Id);

            if (config == null)
            {
                return null;
            }

            return config;

        }

        public async Task<ConfigMaster?> Update(int id, ConfigMaster configChanges)
        {
            if (id != configChanges.ConfigId)
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
                if (!EmployeeExists(id))
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
        private bool EmployeeExists(int id)
        {
            return (context.ConfigMasters?.Any(e => e.ConfigId == id)).GetValueOrDefault();
        }

    }
}