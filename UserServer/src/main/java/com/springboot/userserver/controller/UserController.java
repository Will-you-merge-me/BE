package com.springboot.userserver.controller;

import com.springboot.userserver.data.dto.*;
import com.springboot.userserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup") // 유저 생성
    public ResponseEntity<UserDto.SignupDto> signUp(@RequestPart(value = "userDto") UserDto.SignupDto userDto,
                                                    @RequestPart(value = "image", required = false)MultipartFile image) {
        UserDto.SignupDto createUser = userService.signupUser(userDto,image);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @PostMapping("/signup/trainer")
    public ResponseEntity<UserDto.SignupDto> signUpTrainer(@RequestPart(value = "trainerDto") TrainerSignupRequestDto trainerDto,
                                                           @RequestPart(value = "image", required = false)MultipartFile profileImage,
                                                           @RequestPart(value = "certification") MultipartFile certification) {
        UserDto.SignupDto createTrainer = userService.signupTrainer(trainerDto.getUser(), profileImage, certification);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTrainer);
    }
    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signIn(@RequestBody UserDto.LoginDto loginDto) {
        TokenDto tokenDto = userService.loginUser(loginDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(tokenDto);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserResponseDto>  readUserByUid(@PathVariable("userId") String userId) {
        UserResponseDto userResponseDto = userService.readUserByUid(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }
    @PatchMapping("/patch/{userId}")    // 유저 수정
    public ResponseEntity<TokenDto> updateUser(@PathVariable("userId") String uid,
                                              @RequestBody UserDto.SignupDto userDto) {
        TokenDto updateUser = userService.updateUser(uid, userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateUser);
    }

    @DeleteMapping("/delete/{userId}")  // 유저 삭제
    public void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }

    //이 밑으로 Feign

    @GetMapping("/feign/{userId}")
    public UserFeignDto findById(@PathVariable("userId") Long userId) {
        log.info("User Feign 호출");
        return userService.findById(userId);
    }
}
