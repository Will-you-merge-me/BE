package com.example.reviewserver.dto;

import com.example.reviewserver.entity.Review;
import com.example.reviewserver.entity.ReviewKindOf;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ReviewDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String memo;
    private Float star;
    private String picture;
    private ReviewKindOf reviewKindOf;
    private LocalDate createdDate;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.userId = review.getUserId();
        this.productId = review.getProductId();
        this.memo = review.getMemo();
        this.star = review.getStar();
        this.picture = review.getPicture();
        this.reviewKindOf = review.getReviewKindOf();
        this.createdDate = review.getCreatedDate();
    }
}
