package com.example.reviewserver.entity;

import com.example.reviewserver.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float star; // 별점

    private String memo;    // 내용

    private String picture; // 사진

    private LocalDate createdDate;  // 작성일

    private Long userId;    // 유저 ID

    private Long productId; // 상품 ID

}
