package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.entities.ConfigMaster;
import com.example.services.ConfigMasterService;

@RestController
public class ConfigMasterController {

    @Autowired
    private ConfigMasterService configMasterService;

    @GetMapping("/configMasters")
    public List<ConfigMaster> getAllConfigMasters() {
        return configMasterService.getAllConfigMasters();
    }

    @GetMapping("/configMasters/{id}")
    public ConfigMaster getConfigMasterById(@PathVariable Long id) {
        return configMasterService.getConfigMasterById(id);
    }

    @PostMapping("/configMasters")
    public ConfigMaster saveConfigMaster(@RequestBody ConfigMaster configMaster) {
        return configMasterService.saveConfigMaster(configMaster);
    }

    @PutMapping("/configMasters/{id}")
    public ConfigMaster updateConfigMaster(@PathVariable Long id, @RequestBody ConfigMaster configMaster) {
        ConfigMaster existingConfigMaster = configMasterService.getConfigMasterById(id);
        if (existingConfigMaster != null) {
            existingConfigMaster.setConfigName(configMaster.getConfigName());
            existingConfigMaster.setRemarks(configMaster.getRemarks());
            return configMasterService.saveConfigMaster(existingConfigMaster);
        }
        return null;
    }

    @DeleteMapping("/configMasters/{id}")
    public void deleteConfigMasterById(@PathVariable Long id) {
        configMasterService.deleteConfigMasterById(id);
    }
}