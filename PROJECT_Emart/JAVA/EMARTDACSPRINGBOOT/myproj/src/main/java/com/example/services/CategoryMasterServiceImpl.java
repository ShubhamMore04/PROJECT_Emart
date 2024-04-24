package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.CategoryMaster;
import com.example.repositeries.CategoryMasterRepository;

@Service
public class CategoryMasterServiceImpl implements CategoryMasterService
{
	@Autowired
	private CategoryMasterRepository categoryMasterRepository;

	@Override
	public void deleteCategory(int categoryId) 
	{
		CategoryMaster c=categoryMasterRepository.findById(categoryId).get();
		if(c!=null) {
			categoryMasterRepository.delete(c);
		}	
	}

	//update
    @Override
    public CategoryMaster updateCategory(int categoryId, CategoryMaster updatedCategory) {
        CategoryMaster existingCategory = categoryMasterRepository.findById(categoryId).orElse(null);

        if (existingCategory != null) {
            
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
            existingCategory.setChildflag(updatedCategory.isChildflag());
            existingCategory.setParentCatID(updatedCategory.getParentCatID());
            existingCategory.setCatImgPath(updatedCategory.getCatImgPath()); 
            return categoryMasterRepository.save(existingCategory);
        }

        return null; 
    }

    //insert
	@Override
	public CategoryMaster addCategory(CategoryMaster category) {
		
		return categoryMasterRepository.save(category);
	}

	//getbyID
	@Override
	public CategoryMaster getCategoryById(int categoryId) {
		
		return categoryMasterRepository.findById(categoryId).get();
	}

	//GetAll
	@Override
	public List<CategoryMaster> getAllCategorys() 
	{
		return categoryMasterRepository.findAll();
		
	}
	
	public List<CategoryMaster> getCategoriesByCategoryName(String categoryName) {
        return categoryMasterRepository.findByCategoryName(categoryName);
    }
	
	@Override
	public List<CategoryMaster> findByParentCatID(int id) {
		// TODO Auto-generated method stub
		return categoryMasterRepository.findByParentCatID(id);
	}

}
