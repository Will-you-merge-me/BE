package com.example.paymentserver.Dao;

import com.example.paymentserver.Entity.PaymentEntity;
import com.example.paymentserver.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDaoImpl implements PaymentDao{

    private final PaymentRepository paymentRepository;

    public PaymentDaoImpl(@Autowired PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void createPayment(PaymentEntity paymentEntity) {
        paymentRepository.save(paymentEntity);
    }

    @Override
    public void refundPayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public List<PaymentEntity> findByPayment(Long userId) {
        return paymentRepository.findByUserId(userId);
    }
}
