package com.example.services;

import com.example.entities.ConfigMaster;
import com.example.repositories.ConfigMasterRepository;
import com.example.services.ConfigMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigMasterServiceImpl implements ConfigMasterService {

    private final ConfigMasterRepository configMasterRepository;

    @Autowired
    public ConfigMasterServiceImpl(ConfigMasterRepository configMasterRepository) {
        this.configMasterRepository = configMasterRepository;
    }

    @Override
    public ConfigMaster saveConfig(ConfigMaster config) {
        return configMasterRepository.save(config);
    }

    @Override
    public List<ConfigMaster> getAllConfigs() {
        return configMasterRepository.findAll();
    }

    @Override
    public ConfigMaster getConfigById(int id) {
        return configMasterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Config not found with id: " + id));
    }

    @Override
    public void deleteConfig(int id) {
        configMasterRepository.deleteById(id);
    }
}
