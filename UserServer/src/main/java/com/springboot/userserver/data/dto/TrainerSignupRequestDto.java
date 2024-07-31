package com.springboot.userserver.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TrainerSignupRequestDto {
    private UserDto.SignupDto user;
    private List<CertificationDto> documents;
}
