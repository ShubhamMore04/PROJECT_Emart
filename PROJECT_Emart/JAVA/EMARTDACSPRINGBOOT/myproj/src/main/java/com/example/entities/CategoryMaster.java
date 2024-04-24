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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="CategoryMaster")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CATMASTER_ID")
	private int catmasterID;
		
	private String categoryName;
		
	private boolean childflag;
	
	private int parentCatID;
	
	private String catImgPath;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="catmasterID")
	private List<ProductMaster> Product;
	
	


	
}
