package com.springboot.userserver.service;

import com.springboot.userserver.data.dto.CertificationDto;
import com.springboot.userserver.data.dto.TokenDto;
import com.springboot.userserver.data.dto.UserDto;
import com.springboot.userserver.data.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto.SignupDto signupUser(UserDto.SignupDto userDto);
    UserDto.SignupDto signupTrainer(UserDto.SignupDto userDto, List<CertificationDto> certificationDto);
    TokenDto loginUser(UserDto.LoginDto loginDto);
    UserResponseDto readUserByUid(String userId);
    TokenDto updateUser(String uid, UserDto.SignupDto userDto);

    void deleteUser(String userId);
}
