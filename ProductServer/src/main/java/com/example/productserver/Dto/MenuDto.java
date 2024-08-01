package com.example.productserver.Dto;

import com.example.productserver.Entity.MenuEntity;
import com.example.productserver.Entity.ProductEntity;
import lombok.Builder;

@Builder
public class MenuDto {

    private Long productId;

    private String title; //이름
    private String content; //내용
    private long price; //가격
    private String image; //사진
    private long hour; //강의 시간

    public static MenuEntity dtoToEntity(MenuDto menuDto) {
        return MenuEntity.builder()
                .
                build();
    }
}
