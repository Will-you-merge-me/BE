package com.example.productserver.Dto;

import lombok.Builder;

@Builder
public class CategoryDto {
    private Long id;
    private String largeCategory;
    private String smallCategory;
}
