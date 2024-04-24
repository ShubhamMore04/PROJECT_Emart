package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.ConfigMaster;
import com.example.services.ConfigMasterService;

@RestController
@CrossOrigin
@RequestMapping("/Config")
public class ConfigMasterController 
{
	@Autowired
	private ConfigMasterService configMasterService;
	
	@GetMapping
	public ResponseEntity<?> getAllConfigs(){
		return new ResponseEntity<> (configMasterService.getAllConfigs(),HttpStatus.OK);
	}
	
	
	@GetMapping("/{ConfigId}")
	public ResponseEntity<?> getConfigId(@PathVariable int ConfigId) {
		return new ResponseEntity<> (configMasterService.getConfigById(ConfigId),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addConfig(@RequestBody ConfigMaster Config) {
		return new ResponseEntity<> (configMasterService.addConfig(Config),HttpStatus.CREATED);
	}
	
	@PutMapping("/{ConfigId}")
	public ResponseEntity<?> updateConfig(@PathVariable int ConfigId,@RequestBody ConfigMaster updatedConfig) {
		return new ResponseEntity<> (configMasterService.updateConfig(ConfigId,updatedConfig),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{ConfigId}")
      public void deleteConfig(@PathVariable int ConfigId) {
    	  configMasterService.deleteConfig(ConfigId);
      }
	

}
