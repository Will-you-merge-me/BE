package com.springboot.userserver.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrainerSignupRequestDto {
    private UserDto.SignupDto user;
    private CertificationDto documents;
}
