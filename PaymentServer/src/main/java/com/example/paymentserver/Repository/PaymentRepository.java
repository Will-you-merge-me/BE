package com.example.paymentserver.Repository;

import com.example.paymentserver.Entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
    List<PaymentEntity> findByUserId(Long userId);
}
