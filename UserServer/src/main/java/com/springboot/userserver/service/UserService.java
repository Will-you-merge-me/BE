package com.springboot.userserver.service;

import com.springboot.userserver.data.dto.TokenDto;
import com.springboot.userserver.data.dto.UserDto;
import com.springboot.userserver.data.dto.UserResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto.SignupDto signupUser(UserDto.SignupDto userDto);

    TokenDto loginUser(UserDto.LoginDto loginDto);
    UserResponseDto readUserByUid(String userId);
    TokenDto updateUser(String uid, UserDto.SignupDto userDto);

    void deleteUser(String userId);
}
