package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.ConfigMaster;
import com.example.repositories.ConfigMasterRepository;

@Service
public class ConfigMasterServiceImpl implements ConfigMasterService {

    @Autowired
    private ConfigMasterRepository configMasterRepository;

    @Override
    public List<ConfigMaster> getAllConfigMasters() {
        return configMasterRepository.findAll();
    }

    @Override
    public ConfigMaster getConfigMasterById(Long id) {
        return configMasterRepository.findById(id).orElse(null);
    }

    @Override
    public ConfigMaster saveConfigMaster(ConfigMaster configMaster) {
        return configMasterRepository.save(configMaster);
    }

    @Override
    public void deleteConfigMasterById(Long id) {
        configMasterRepository.deleteById(id);
    }
}