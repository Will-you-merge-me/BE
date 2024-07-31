package com.springboot.userserver.data.dto;


import com.springboot.userserver.data.entity.UserEntity;
import com.springboot.userserver.data.entity.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
// userDto에 (signupDto, loginDto, trainerSignupDto) 추가할 것 나머진 알아서

public class UserDto {
    @Getter @Setter
    @Builder
    public static class SignupDto {
        private String uid;
        private String password;

        private String name;
        private String phone;
        private String nickname;
        private String address;
        private String imageURL;
        private String role;
        public static UserEntity dtoToEntity(UserDto.SignupDto signupDto) {
            return UserEntity.builder()
                    .uid(signupDto.getUid())
                    .password(signupDto.getPassword())
                    .name(signupDto.getName())
                    .phone(signupDto.getPhone())
                    .nickname(signupDto.getNickname())
                    .address(signupDto.getAddress())
                    .imageURL(signupDto.getImageURL())
                    .role(Role.valueOf(signupDto.getRole()))
                    .build();
        }

        public static SignupDto entityToDto(UserEntity userEntity) {
            return SignupDto.builder()
                    .uid(userEntity.getUid())
                    .password(userEntity.getPassword())
                    .name(userEntity.getName())
                    .phone(userEntity.getPhone())
                    .nickname(userEntity.getNickname())
                    .address(userEntity.getAddress())
                    .imageURL(userEntity.getImageURL())
                    .role(String.valueOf(userEntity.getRole()))
                    .build();
        }
    }

    @Getter
    @Builder
    public static class LoginDto {
        @NotBlank
        private String uid;
        @NotBlank
        private String password;

        public static UserEntity dtoToEntity(UserDto.LoginDto loginDto) {
            return UserEntity.builder()
                    .uid(loginDto.getUid())
                    .password(loginDto.getPassword())
                    .build();
        }

        public static LoginDto entityToDto(UserEntity userEntity) {
            return LoginDto.builder()
                    .uid(userEntity.getUid())
                    .password(userEntity.getPassword())
                    .build();
        }
    }
}
