package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ConfigMaster")
public class ConfigMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int configId;
    private String configName;
    private String remarks;

    public ConfigMaster() {
    	
    }

    public ConfigMaster(String configName, String remarks) {
        this.configName = configName;
        this.remarks = remarks;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}




