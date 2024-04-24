using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Controllers
{
    [Route("/ConfigDetail")]
    [ApiController]
    public class ConfigDetailMasterController : ControllerBase
    {
        private readonly IConfigDetailMasterRepository _repository;


        public ConfigDetailMasterController(IConfigDetailMasterRepository repository)
        {
            _repository = repository;

        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<ConfigDetailMaster>>?> GetConfigDetailMasters()
        {
            if (await _repository.GetAllConfig() == null)
            {
                return NotFound();
            }

            return await _repository.GetAllConfig();
        }
        
        [HttpGet("{id}")]
        public async Task<ActionResult<ConfigDetailMaster>> GetById_ActionResultOfT(int id)
        {
            var config = await _repository.GetConfig(id);
            return config == null ? NotFound() : config;
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> PutConfigDetailMaster(int id, ConfigDetailMaster config)
        {
            if (id != config.ConfigDetailsid)
            {
                return BadRequest();
            }
            try
            {
                await _repository.Update(id, config);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (_repository.GetConfig(id) == null)
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
        public async Task<ActionResult<ConfigDetailMaster>> PostConfigDetails(ConfigDetailMaster config)
        {
            await _repository.Add(config);
            return CreatedAtAction(nameof(GetById_ActionResultOfT), new { id = config.ConfigDetailsid }, config);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteConfigDetailMaster(int id)
        {
            if (_repository.GetAllConfig() == null)
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