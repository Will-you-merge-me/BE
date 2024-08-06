package com.example.paymentserver.Dao;

import com.example.paymentserver.Entity.PaymentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao {
    void createPayment(PaymentEntity paymentEntity);

    void refundPayment(Long paymentId);

    List<PaymentEntity> findByPayment(Long userId);
}
