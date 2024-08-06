package com.springboot.userserver.data.repository;

import com.springboot.userserver.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUid(String uid);
    boolean existsByNickname(String nickname);

    Optional<UserEntity> getByUid(String uid);

    UserEntity findByUid(String uid);
}
