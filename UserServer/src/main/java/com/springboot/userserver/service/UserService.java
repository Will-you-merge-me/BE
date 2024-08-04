package com.springboot.userserver.service;

import com.springboot.userserver.data.dto.TokenDto;
import com.springboot.userserver.data.dto.UserDto;
import com.springboot.userserver.data.dto.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
    UserDto.SignupDto signupUser(UserDto.SignupDto userDto, MultipartFile image);
    UserDto.SignupDto signupTrainer(UserDto.SignupDto userDto, MultipartFile profileImage, MultipartFile certification);
    TokenDto loginUser(UserDto.LoginDto loginDto);
    UserResponseDto readUserByUid(String userId);
    TokenDto updateUser(String uid, UserDto.SignupDto userDto);

    void deleteUser(String userId);
}
