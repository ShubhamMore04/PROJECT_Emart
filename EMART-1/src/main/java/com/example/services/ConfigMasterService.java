package com.example.services;

import com.example.entities.ConfigMaster;

import java.util.List;

public interface ConfigMasterService {
	
    public ConfigMaster saveConfig(ConfigMaster config);
    public List<ConfigMaster> getAllConfigs();
    public ConfigMaster getConfigById(int id);
    public void deleteConfig(int id);
}
