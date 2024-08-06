package com.example.paymentserver.Dto;

import com.example.paymentserver.Entity.PaymentEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class PaymentDto {
    private Long productId;
    private Long menuId;
    private Long userId;
    private LocalDate buyDate;
    private LocalDate lessonDate;

    public static PaymentEntity dtoToEntity(PaymentDto paymentDto){
        return PaymentEntity.builder().
                productId(paymentDto.getProductId()).
                menuId(paymentDto.getMenuId()).
                userId(paymentDto.getUserId()).
                lessonDate(paymentDto.getLessonDate()).
                build();
    }

    public static PaymentDto entityToDto(PaymentEntity paymentEntity){
        return PaymentDto.builder().
                productId(paymentEntity.getProductId()).
                menuId(paymentEntity.getMenuId()).
                userId(paymentEntity.getUserId()).
                buyDate(paymentEntity.getBuyDate()).
                lessonDate(paymentEntity.getLessonDate()).
                build();
    }
}
