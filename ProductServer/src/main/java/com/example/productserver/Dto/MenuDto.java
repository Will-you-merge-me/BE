package com.example.productserver.Dto;

import com.example.productserver.Entity.ProductEntity;


public class MenuDto {

    private ProductEntity productEntity;

    private String title; //이름
    private String content; //내용
    private long price; //가격
    private String image; //사진
    private long hour; //강의 시간
}
