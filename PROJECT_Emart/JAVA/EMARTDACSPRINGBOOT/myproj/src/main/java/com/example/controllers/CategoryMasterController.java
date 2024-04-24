package com.example.controllers;

import java.util.List;

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

import com.example.entities.CategoryMaster;
import com.example.services.CategoryMasterService;


@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/Category")
public class CategoryMasterController 
{
	@Autowired
	private CategoryMasterService categoryMasterService ;
	
	@GetMapping
	public ResponseEntity<?> getAllCategorys(){
		return new ResponseEntity<> ( categoryMasterService.getAllCategorys(),HttpStatus.OK);
		}
	
	
	@GetMapping("/{CategoryId}")
	public ResponseEntity<?> getCategoryId(@PathVariable int CategoryId) {
		return new ResponseEntity<> (categoryMasterService.getCategoryById(CategoryId),HttpStatus.OK);
	}
	
	@GetMapping("/byName/{categoryName}")
    public ResponseEntity<List<CategoryMaster>> getCategoriesByCategoryName(@PathVariable String categoryName) {
        List<CategoryMaster> categories = categoryMasterService.getCategoriesByCategoryName(categoryName);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
	
	@PostMapping 
	public ResponseEntity<?> addCategory(@RequestBody CategoryMaster Category) {
		return new ResponseEntity<>( categoryMasterService.addCategory(Category),HttpStatus.CREATED);
	}
	
	@PutMapping("/{CategoryId}")
	public ResponseEntity<?> updateCategory(@PathVariable int CategoryId,@RequestBody CategoryMaster updatedCategory) {
		return new ResponseEntity<> (categoryMasterService.updateCategory(CategoryId, updatedCategory),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{CategoryId}")
      public void deleteCategory(@PathVariable int CategoryId) {
    	  categoryMasterService.deleteCategory(CategoryId);
      }
	
	@GetMapping("getCatNameByParentId/{Id}")
	public ResponseEntity<?> findByParentCatID(@PathVariable int Id) {
		return new ResponseEntity<> (categoryMasterService.findByParentCatID(Id),HttpStatus.OK);
	}
	

}
