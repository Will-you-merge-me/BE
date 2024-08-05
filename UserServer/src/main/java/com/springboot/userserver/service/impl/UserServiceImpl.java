package com.springboot.userserver.service.impl;

import com.springboot.userserver.Config.JwtTokenProvider;
import com.springboot.userserver.data.dto.*;
import com.springboot.userserver.data.entity.CertificationEntity;
import com.springboot.userserver.data.entity.UserEntity;
import com.springboot.userserver.data.repository.CertificationRepository;
import com.springboot.userserver.data.repository.UserRepository;
import com.springboot.userserver.service.S3UploadUtil;
import com.springboot.userserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final S3UploadUtil s3UploadUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           CertificationRepository certificationRepository,
                           JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder,
                           S3UploadUtil s3UploadUtil) {
        this.userRepository = userRepository;
        this.certificationRepository = certificationRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.s3UploadUtil = s3UploadUtil;
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
    @Transactional
    public UserDto.SignupDto signupUser(UserDto.SignupDto signupDto, MultipartFile image) {
        validateDuplicateEmail(signupDto.getUid());
        validateDuplicateNickname(signupDto.getNickname());

        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        URL uploadUrl = null;
        if(!image.isEmpty()){
            String PRODUCT_IMG_DIR = "user/";
            uploadUrl = s3UploadUtil.fileUpload(image, PRODUCT_IMG_DIR);
        }

        UserEntity userEntity = UserDto.SignupDto.dtoToEntity(signupDto, String.valueOf(uploadUrl));
        userRepository.save(userEntity);

        return UserDto.SignupDto.entityToDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto.SignupDto signupTrainer(UserDto.SignupDto trainerDto, MultipartFile profileImage, MultipartFile certification) {
        validateDuplicateEmail(trainerDto.getUid());
        validateDuplicateNickname(trainerDto.getNickname());

        URL uploadUrl = null; //프로필 이미지 저장
        if(!profileImage.isEmpty()){
            String PRODUCT_IMG_DIR = "trainer/";
            uploadUrl = s3UploadUtil.fileUpload(profileImage, PRODUCT_IMG_DIR);
        }

        UserEntity userEntity = UserDto.SignupDto.dtoToEntity(trainerDto, String.valueOf(uploadUrl));
        UserEntity savedUserEntity = userRepository.save(userEntity);

        URL certificationUrl = null; //인증서류 저장
        if(!certification.isEmpty()){
            String PRODUCT_IMG_DIR = "certification/";
            certificationUrl = s3UploadUtil.fileUpload(certification, PRODUCT_IMG_DIR);
        }

        CertificationEntity certificationEntity = CertificationDto.dtoToEntity(String.valueOf(certificationUrl), savedUserEntity);

        certificationRepository.save(certificationEntity);

        return UserDto.SignupDto.entityToDto(savedUserEntity);
    }
    @Override
    public TokenDto loginUser(UserDto.LoginDto loginDto) {
        UserEntity userEntity = userRepository.getByUid(loginDto.getUid())
                .orElseThrow(() -> new IllegalArgumentException("회원정보가 일치하지 않습니다."));

        if(!passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            throw new IllegalArgumentException("회원정보가 일치하지 않습니다.");
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
    @Transactional
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
    @Transactional
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.getByUid(userId)
                .orElseThrow(() -> new IllegalArgumentException("대상 회원이 존재하지 않습니다."));

        userRepository.delete(userEntity);
    }

    @Override
    public UserFeignDto findById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        return UserFeignDto.entityToDto(userEntity);
    }
}
