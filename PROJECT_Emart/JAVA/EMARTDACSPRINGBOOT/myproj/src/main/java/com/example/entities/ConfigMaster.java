package com.example.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ConfigMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Config_ID")
  private int ConfigID;
  private String ConfigName;
  
  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name="ConfigID")
  private List<ConfigDetailMaster> config_details;
	
	
	
		 
  

	
	
		  
		  
		}
