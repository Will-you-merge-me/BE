package com.example.productserver.Dto;

import com.example.productserver.Entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Setter
@Getter
public class ProductResponseDto {
    private Long productId;
    private String userName; //트레이너 이름
    private String profileImage; //트레이너 프로필 이미지
    private String phoneNum; //트레이너 휴대폰 번호
    private String largeCategory; //대분류
    private String smallCategory; //소분류

    private String title;
    private String description; //상품 내용
    private String location; //강의 장소
    private String image; //상품 사진
    private Long productLike; //상품 좋아요
    private Float reviewScore; //상품 평균 점수
    private String my_description; //상품 소개
    private Date startTime; //강의 시작 시간
    private Date endTime; //강의 종료 시간
    private String closeDay; //휴무일

    public static ProductResponseDto entityToDto(ProductEntity productEntity, UserFeignDto userFeignDto, ReviewFeignDto reviewFeignDto) {
        return ProductResponseDto.builder().
                productId(productEntity.getId()).
                userName(userFeignDto.getName()).
                profileImage(userFeignDto.getProfileImage()).
                phoneNum(userFeignDto.getPhoneNum()).
                largeCategory(productEntity.getCategoryEntity().getLargeCategory()).
                smallCategory(productEntity.getCategoryEntity().getSmallCategory()).
                title(productEntity.getTitle()).
                description(productEntity.getDescription()).
                location(productEntity.getLocation()).
                image(productEntity.getImage()).
                productLike(productEntity.getProductLike()).
                reviewScore(reviewFeignDto.getReviewScore()).
                my_description(productEntity.getMy_description()).
                startTime(productEntity.getStartTime()).
                endTime(productEntity.getEndTime()).
                closeDay(productEntity.getCloseDay()).
                build();
    }
}
