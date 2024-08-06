package com.example.paymentserver.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; //상품번호
    private Long id; //결제번호
    private Long menuId; //메뉴번호
    private Long userId; //회원번호
    private LocalDate buyDate; //구매날짜
    private LocalDate lessonDate; //강의날짜

    @PrePersist // 엔티티를 저장 또는 업데이트할 때 실행되는 메서드
    public void prePersist() {
        if (this.buyDate == null)
            this.buyDate = LocalDate.now();
    }
}
