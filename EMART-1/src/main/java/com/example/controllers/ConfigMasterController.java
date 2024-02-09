package com.example.controllers;

import com.example.entities.ConfigMaster;
import com.example.services.ConfigMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configs")
public class ConfigMasterController {

    private final ConfigMasterService configMasterService;

    @Autowired
    public ConfigMasterController(ConfigMasterService configMasterService) {
        this.configMasterService = configMasterService;
    }

    @GetMapping
    public ResponseEntity<List<ConfigMaster>> getAllConfigs() {
        List<ConfigMaster> configs = configMasterService.getAllConfigs();
        return ResponseEntity.ok(configs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfigMaster> getConfigById(@PathVariable int id) {
        ConfigMaster config = configMasterService.getConfigById(id);
        return ResponseEntity.ok(config);
    }

    @PostMapping
    public ResponseEntity<ConfigMaster> createConfig(@RequestBody ConfigMaster config) {
        ConfigMaster savedConfig = configMasterService.saveConfig(config);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConfig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfig(@PathVariable int id) {
        configMasterService.deleteConfig(id);
        return ResponseEntity.noContent().build();
    }
}
