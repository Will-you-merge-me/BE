package com.example.paymentserver.Service;

import com.example.paymentserver.Dao.PaymentDao;
import com.example.paymentserver.Dto.PaymentDto;
import com.example.paymentserver.Entity.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentDao paymentDao;

    public PaymentServiceImpl(@Autowired PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        PaymentEntity paymentEntity = PaymentDto.dtoToEntity(paymentDto);
        paymentDao.createPayment(paymentEntity);
        return PaymentDto.entityToDto(paymentEntity);
    }

    @Override
    public PaymentDto findByPayment(Long userId) {
        List<PaymentEntity> list = paymentDao.findByPayment(userId);
//        return list.stream()
//                .map(productEntity -> PaymentResponseDto.entityToDto(productEntity, userFeignClient.findById(productEntity.getUserId()),
//                        reviewFeignClient.avgStarByProductId(productEntity.getId())))
//                .collect(Collectors.toList());
        return null;
    }


    @Override
    public void refundPayment(Long paymentId) {
        paymentDao.refundPayment(paymentId);
    }
}
