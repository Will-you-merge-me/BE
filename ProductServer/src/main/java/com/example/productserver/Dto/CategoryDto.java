package com.example.productserver.Dto;

import com.example.productserver.Entity.CategoryEntity;
import lombok.Builder;

@Builder
public class CategoryDto {
    private Long id;
    private String largeCategory;
    private String smallCategory;

    public static CategoryEntity dtoToEntity(CategoryDto categoryDto){
        return CategoryEntity.builder().
                largeCategory(categoryDto.largeCategory).
                smallCategory(categoryDto.smallCategory).
                build();
    }

    public static CategoryDto entityToDto(CategoryEntity categoryEntity){
        return CategoryDto.builder().
                id(categoryEntity.getId()).
                largeCategory(categoryEntity.getLargeCategory()).
                smallCategory(categoryEntity.getSmallCategory()).
                build();
    }
}
