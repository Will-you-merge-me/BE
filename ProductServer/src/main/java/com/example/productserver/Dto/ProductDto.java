package com.example.productserver.Dto;

import java.util.Date;

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
}
