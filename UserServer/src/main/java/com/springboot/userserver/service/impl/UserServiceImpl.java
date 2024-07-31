package com.springboot.userserver.service.impl;

import com.springboot.userserver.Config.JwtTokenProvider;
import com.springboot.userserver.data.dto.TokenDto;
import com.springboot.userserver.data.dto.UserDto;
import com.springboot.userserver.data.dto.UserResponseDto;
import com.springboot.userserver.data.entity.UserEntity;
import com.springboot.userserver.data.repository.UserRepository;
import com.springboot.userserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public void validateDuplicateEmail(String uid) {
        if(userRepository.existsByUid(uid)) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }
    }
    public void validateDuplicateNickname(String nickname) {
        if(userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("닉네임이 이미 존재합니다.");
        }
    }
    @Override
    public UserDto.SignupDto signupUser(UserDto.SignupDto signupDto) {
        validateDuplicateEmail(signupDto.getUid());
        validateDuplicateNickname(signupDto.getNickname());

        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        UserEntity userEntity = UserDto.SignupDto.dtoToEntity(signupDto);
        userRepository.save(userEntity);

        return UserDto.SignupDto.entityToDto(userEntity);
    }

    @Override
    public TokenDto loginUser(UserDto.LoginDto loginDto) {
        UserEntity userEntity = userRepository.getByUid(loginDto.getUid())
                .orElseThrow(() -> new IllegalArgumentException("아이디(로그인 전화번호, 로그인 전용 아이디) 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요."));

        if(!passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            throw new IllegalArgumentException("아이디(로그인 전화번호, 로그인 전용 아이디) 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
        }

        TokenDto tokenDto = new TokenDto();
        tokenDto.setKeyId(userEntity.getId());
        tokenDto.setRole(String.valueOf(userEntity.getRole())); //
        tokenDto.setNickname(userEntity.getNickname());
        tokenDto.setToken(jwtTokenProvider.createToken(userEntity.getNickname(), String.valueOf(userEntity.getRole())));

        logger.info("로그인 성공");
        return tokenDto;
    }

    @Override
    public UserResponseDto readUserByUid(String userId) {
        UserEntity userEntity = userRepository.getByUid(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        return UserResponseDto.entityToDto(userEntity);
    }

    @Override
    public TokenDto updateUser(String uid, UserDto.SignupDto userDto) {
        UserEntity userEntity = userRepository.findByUid(uid);

        if(userDto.getPassword() != null) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userEntity.setPassword(userDto.getPassword());
        }
        if(userDto.getNickname() != null) {
            userEntity.setNickname(userDto.getNickname());
        }
        if (userDto.getAddress() != null) {
            userEntity.setAddress(userDto.getAddress());
        }
        if (userDto.getImageURL() != null) {
            userEntity.setImageURL(userDto.getImageURL());
        }
        userRepository.save(userEntity);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setNickname(userEntity.getNickname());
        tokenDto.setToken(jwtTokenProvider.createToken(userEntity.getName(), String.valueOf(userEntity.getRole())));
        logger.info("회원 정보 수정 후 토큰을 재발급했습니다.");

        return tokenDto;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.getByUid(userId)
                .orElseThrow(() -> new IllegalArgumentException("대상 회원이 존재하지 않습니다."));

        userRepository.delete(userEntity);
    }
}
