package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.ConfigMaster;
import com.example.repositeries.ConfigMasterRepository;

@Service
public class ConfigMasterServiceImpl implements ConfigMasterService
{
	@Autowired
	private ConfigMasterRepository configMasterRepository;

	//getall
	@Override
	public List<ConfigMaster> getAllConfigs() {
		
		return configMasterRepository.findAll();
	}

	//getbyID
	@Override
	public ConfigMaster getConfigById(int configId) {
		
		return configMasterRepository.findById(configId).get();
	}

	//insert
	@Override
	public ConfigMaster addConfig(ConfigMaster config) {
		
		return configMasterRepository.save(config);
	}


	//update
    @Override
    public ConfigMaster updateConfig(int configId, ConfigMaster updatedConfig) {
        ConfigMaster existingConfig = configMasterRepository.findById(configId).orElse(null);

        if (existingConfig != null) {
           
            existingConfig.setConfigName(updatedConfig.getConfigName());

            
            return configMasterRepository.save(existingConfig);
        }

        return null; 
    }

    //delete
	@Override
	public void deleteConfig(int configId) 
	{
		ConfigMaster c= configMasterRepository.findById(configId).get();
		if(c!=null) {
			configMasterRepository.delete(c);
		}
	}

}
