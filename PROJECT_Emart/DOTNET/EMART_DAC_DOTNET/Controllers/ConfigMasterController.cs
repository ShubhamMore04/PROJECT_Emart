using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Controllers
{
    [Route("/Config")]
    [ApiController]
    public class ConfigMasterController : ControllerBase
    {
        private readonly IConfigMasterRepository _repository;

        public ConfigMasterController(IConfigMasterRepository repository)
        {
            _repository = repository;

        }
        [HttpGet]
        public async Task<ActionResult<IEnumerable<ConfigMaster>>?> GetConfigMasters()
        {
            if (await _repository.GetAllConfigMaster() == null)
            {
                return NotFound();
            }

            return await _repository.GetAllConfigMaster();
        }
        [HttpGet("{id}")]
        public async Task<ActionResult<ConfigMaster>> GetById_ActionResultOfT(int id)
        {
            var config = await _repository.GetConfigMaster(id);
            return config == null ? NotFound() : config;
        }
        [HttpPut("{id}")]
        public async Task<IActionResult> PutConfig(int id, ConfigMaster config)
        {
            if (id != config.ConfigId)
            {
                return BadRequest();
            }
            try
            {
                await _repository.Update(id, config);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (_repository.GetConfigMaster(id) == null)
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }
            return NoContent();
        }

        [HttpPost]
        public async Task<ActionResult<ConfigMaster>> PostConfig(ConfigMaster config)
        {
            await _repository.Add(config);
            return CreatedAtAction(nameof(GetById_ActionResultOfT), new { id = config.ConfigId }, config);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteConfig(int id)
        {
            if (_repository.GetAllConfigMaster() == null)
            {
                return NotFound();
            }
            var config = _repository.Delete(id);
            if (config == null)
            {
                return NotFound();
            }
            await _repository.Delete(config.Id);
            return NoContent();
        }

    }
}
