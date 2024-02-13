package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.CategoryMaster;
import com.example.repositories.CategoryMasterRepository;

@Service
public class CategoryMasterServiceImpl implements CategoryMasterService {

    @Autowired
    private CategoryMasterRepository categoryRepository;

    @Override
    public List<CategoryMaster> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryMaster getCategoryById(int id) {
        Optional<CategoryMaster> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    @Override
    public CategoryMaster saveCategory(CategoryMaster category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}