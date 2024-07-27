package com.example.productserver.Dto;

import com.example.productserver.Entity.CategoryEntity;
import com.example.productserver.Entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

//상품을 등록하기 위한 DTO
@Builder
@Getter
public class ProductDto {
    private Long userId;
    private Long categoryId;

    private String title;
    private String description; //상품 내용
    private String location; //강의 장소
    private String image; //상품 사진
    private Long like; //상품 좋아요

    private Date startTime; //강의 시작 시간
    private Date endTime; //강의 종료 시간
    private String closeDay; //휴무일

    public static ProductEntity dtoToEntity(ProductDto productDto,CategoryEntity categoryEntity) {
        return ProductEntity.builder().
                userId(productDto.userId).
                categoryEntity(categoryEntity).
                title(productDto.title).
                description(productDto.description).
                location(productDto.location).
                image(productDto.image).
                like(productDto.like).
                startTime(productDto.startTime).
                endTime(productDto.endTime).
                closeDay(productDto.closeDay).
                build();
    }

    public static ProductDto entityToDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .userId(productEntity.getUserId())
                .categoryId(productEntity.getCategoryEntity().getId())
                .title(productEntity.getTitle())
                .description(productEntity.getDescription())
                .location(productEntity.getLocation())
                .image(productEntity.getImage())
                .like(productEntity.getLike())
                .startTime(productEntity.getStartTime())
                .endTime(productEntity.getEndTime())
                .closeDay(productEntity.getCloseDay())
                .build();
    }
}
