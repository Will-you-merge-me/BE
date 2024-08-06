package com.springboot.userserver.data.dto;

import com.springboot.userserver.data.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserFeignDto {
    private Long id;
    private String name;
    private String nickname;
    private String profileImage;
    private String phoneNum;

    public static UserFeignDto entityToDto(UserEntity userEntity) {
        return UserFeignDto.builder().
                id(userEntity.getId()).
                name(userEntity.getName()).
                nickname(userEntity.getNickname()).
                profileImage(userEntity.getImageURL()).
                phoneNum(userEntity.getPhone()).
                build();
    }
}
