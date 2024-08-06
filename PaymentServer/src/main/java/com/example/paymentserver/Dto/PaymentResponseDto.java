package com.example.paymentserver.Dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class PaymentResponseDto {
    private String location; //강의 장소
    private String trainerName; //트레이너 이름
    private String trainerPhone; //트레이너 폰번호
    private String trainerImage; //트레이너 프로필 이미지
    private LocalDate buyDate;//구매날짜
    private LocalDate lessonDate; //강의날짜
}
