package com.example.productserver.Dto;

import com.example.productserver.Entity.MenuEntity;
import com.example.productserver.Entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MenuDto {

    private Long productId;

    private String title; //이름
    private String content; //내용
    private Long price; //가격
    private String image; //사진
    private Long hour; //강의 시간

    public static MenuEntity dtoToEntity(MenuDto menuDto, ProductEntity productEntity, String uploadUrl) {
        return MenuEntity.builder().
                productEntity(productEntity).
                title(menuDto.title).
                content(menuDto.content).
                price(menuDto.price).
                image(uploadUrl).
                hour(menuDto.hour).
                build();
    }

    public static MenuDto entityToDto(MenuEntity menuEntity) {
        return MenuDto.builder().
                productId(menuEntity.getProductEntity().getId()).
                title(menuEntity.getTitle()).
                content(menuEntity.getContent()).
                price(menuEntity.getPrice()).
                image(menuEntity.getImage()).
                hour(menuEntity.getHour()).
                build();
    }
}
