package com.example.productserver.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "menu")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //메뉴 번호

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity productEntity;

    private String title; //이름
    private String content; //내용
    private long price; //가격
    private String image; //사진
    private long hour; //강의 시간
}
