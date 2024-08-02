package com.example.reviewserver.dto;

import com.example.reviewserver.entity.Review;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long userId;    // 유저 ID
    private Long productId; // 상품 ID
    private String memo;    // 내용
    private Float star; // 별점
    private String picture; // 사진
    private LocalDate createdDate;  // 작성일

    public static Review dtoToEntity(ReviewDto reviewDto, String uploadUrl) {
        return Review.builder()
                .userId(reviewDto.getUserId())
                .productId(reviewDto.getProductId())
                .memo(reviewDto.getMemo())
                .star(reviewDto.getStar())
                .picture(uploadUrl)
                .createdDate(reviewDto.getCreatedDate())
                .build();
    }

    public static ReviewDto entityToDto(Review review) {
        return ReviewDto.builder()
                .userId(review.getUserId())
                .productId(review.getProductId())
                .memo(review.getMemo())
                .star(review.getStar())
                .picture(review.getPicture())
                .createdDate(review.getCreatedDate())
                .build();
    }

}
