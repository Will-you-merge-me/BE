package com.springboot.userserver.data.dto;

import com.springboot.userserver.data.entity.UserEntity;
import com.springboot.userserver.data.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserResponseDto {

    private Long userId;
    private String uid;
    private String nickname;
    private String address;
    private String role;
    private String imageURL;
    public static UserResponseDto entityToDto(UserEntity userEntity) {
        return UserResponseDto.builder()
                .userId(userEntity.getId())
                .uid(userEntity.getUid())
                .nickname(userEntity.getNickname())
                .address(userEntity.getAddress())
                .role(String.valueOf(userEntity.getRole()))
                .build();
    }

    public static UserEntity dtoToEntity(UserResponseDto userResponseDto) {
        return UserEntity.builder()
                .id(userResponseDto.getUserId())
                .uid(userResponseDto.getUid())
                .nickname(userResponseDto.getNickname())
                .address(userResponseDto.getAddress())
                .role(Role.valueOf(userResponseDto.getRole()))
                .build();
    }
}
