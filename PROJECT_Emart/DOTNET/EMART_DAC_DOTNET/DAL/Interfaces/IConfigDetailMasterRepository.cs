using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.DAL.Interfaces
{
    public interface IConfigDetailMasterRepository
    {
        Task<ActionResult<ConfigDetailMaster>?> GetConfig(int Id);
        Task<ActionResult<IEnumerable<ConfigDetailMaster>>?> GetAllConfig();
        Task<ActionResult<ConfigDetailMaster>> Add(ConfigDetailMaster config);
        Task<ConfigDetailMaster?> Update(int id, ConfigDetailMaster configChanges);
        Task<ConfigDetailMaster?> Delete(int Id);
    }
}
