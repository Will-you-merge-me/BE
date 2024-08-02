package com.example.reviewserver.entity;

import com.example.reviewserver.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float star;

    private String memo;

    private String picture;

    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    private ReviewKindOf reviewKindOf;

    private Long userId;

    private Long productId;

    public void setReview(ReviewDto reviewDto){
        this.star = reviewDto.getStar();
        this.picture = reviewDto.getPicture();
        this.memo = reviewDto.getMemo();
        this.createdDate = LocalDate.now();
        this.userId = reviewDto.getUserId();
        this.productId = reviewDto.getProductId();
    }
}
