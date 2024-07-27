package com.example.productserver.Service;

import com.example.productserver.Dto.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
}
