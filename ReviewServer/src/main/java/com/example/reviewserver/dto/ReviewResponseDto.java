package com.example.reviewserver.dto;

import com.example.reviewserver.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ReviewResponseDto {
    private Long reviewId;
    private Long productId;
    private String name;
    private String nickname;
    private String profileImage;
    private String phoneNum;

    private String memo;    // 리뷰 내용
    private Float star; // 별점
    private String picture; // 사진
    private LocalDate createdDate;  // 작성일

    public static ReviewResponseDto entityToDto(Review review, UserFeignDto userFeignDto) {
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .productId(review.getProductId())
                .name(userFeignDto.getName())
                .nickname(userFeignDto.getNickname())
                .profileImage(userFeignDto.getProfileImage())
                .phoneNum(userFeignDto.getPhoneNum())
                .memo(review.getMemo())
                .star(review.getStar())
                .picture(review.getPicture())
                .createdDate(review.getCreatedDate())
                .build();
    }
}
