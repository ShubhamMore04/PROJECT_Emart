package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Authentication {
	private String custEmail; 
	
	private String custPassword;

	
	
}
