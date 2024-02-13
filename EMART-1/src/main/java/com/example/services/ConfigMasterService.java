package com.example.services;

import java.util.List;
import com.example.entities.ConfigMaster;

public interface ConfigMasterService {
    List<ConfigMaster> getAllConfigMasters();
    ConfigMaster getConfigMasterById(Long id);
    ConfigMaster saveConfigMaster(ConfigMaster configMaster);
    void deleteConfigMasterById(Long id);
}