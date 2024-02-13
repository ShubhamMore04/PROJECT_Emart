package com.example.services;

import java.util.List;

import com.example.entities.CategoryMaster;

public interface CategoryMasterService {
    List<CategoryMaster> getAllCategories();
    CategoryMaster getCategoryById(int id);
    CategoryMaster saveCategory(CategoryMaster category);
    void deleteCategory(int id);
}