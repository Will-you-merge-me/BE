package com.example.reviewserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserFeignDto {
    private Long id;
    private String name;
    private String nickname;
    private String profileImage;
    private String phoneNum;
}
