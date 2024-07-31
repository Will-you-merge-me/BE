package com.springboot.userserver.data.repository;

import com.springboot.userserver.data.entity.CertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<CertificationEntity, Long> {
}
