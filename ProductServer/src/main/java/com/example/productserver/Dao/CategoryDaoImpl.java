package com.example.productserver.Dao;

import com.example.productserver.Entity.CategoryEntity;
import com.example.productserver.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao{

    private final CategoryRepository categoryRepository;

    public CategoryDaoImpl(@Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity findByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId).
                orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
    }

    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity findBySmallCategory(String smallCategory) {
        return categoryRepository.findBySmallCategory(smallCategory);
    }
}
