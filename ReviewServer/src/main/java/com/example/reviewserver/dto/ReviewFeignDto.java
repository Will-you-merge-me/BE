package com.example.reviewserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReviewFeignDto {
    private Float reviewScore;

    public static ReviewFeignDto floatToDto(Float score) {
        return ReviewFeignDto.builder()
                .reviewScore(score)
                .build();
    }
}
