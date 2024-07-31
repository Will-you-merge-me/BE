package com.springboot.userserver.data.dto;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private Long keyId; // 유저 기본키
    private String token;
    private String nickname;
    private String role;
}
