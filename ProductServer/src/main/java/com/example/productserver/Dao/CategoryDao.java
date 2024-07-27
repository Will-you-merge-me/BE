package com.example.productserver.Dao;

import com.example.productserver.Entity.CategoryEntity;

public interface CategoryDao {
    CategoryEntity findByCategoryId(Long categoryId);
    
    CategoryEntity createCategory(CategoryEntity categoryEntity);

    CategoryEntity findBySmallCategory(String smallCategory);
}
