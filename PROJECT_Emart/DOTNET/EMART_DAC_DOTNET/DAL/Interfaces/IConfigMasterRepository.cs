using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;

namespace EMART_DAC.DAL.Interfaces
{
    public interface IConfigMasterRepository
    {
        Task<ActionResult<ConfigMaster>?> GetConfigMaster(int Id);
        Task<ActionResult<IEnumerable<ConfigMaster>>?> GetAllConfigMaster();
        Task<ActionResult<ConfigMaster>> Add(ConfigMaster config);
        Task<ConfigMaster?> Update(int id, ConfigMaster configChanges);
        Task<ConfigMaster?> Delete(int Id);
    }
}
