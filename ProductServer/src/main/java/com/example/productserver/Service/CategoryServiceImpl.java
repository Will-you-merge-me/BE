package com.example.productserver.Service;

import com.example.productserver.Dao.CategoryDao;
import com.example.productserver.Dto.CategoryDto;
import com.example.productserver.Entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(@Autowired CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryDao.findBySmallCategory(categoryDto.getSmallCategory());
        if (categoryEntity != null) {
            return CategoryDto.entityToDto(categoryEntity);
        }

        categoryEntity = CategoryDto.dtoToEntity(categoryDto);
        categoryEntity = categoryDao.createCategory(categoryEntity);
        return CategoryDto.entityToDto(categoryEntity);
    }
}
