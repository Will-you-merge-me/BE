package com.example.productserver.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품 번호
    private Long userId; //회원 번호

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity categoryEntity;//카테고리 번호

    private String title; //상품 제목
    private String description; //상품 내용
    private String location; //강의 장소
    private String image; //상품 사진
    private Long like; //상품 좋아요

    private Date startTime; //강의 시작 시간
    private Date endTime; //강의 종료 시간
    private String rest; //휴무일
}
