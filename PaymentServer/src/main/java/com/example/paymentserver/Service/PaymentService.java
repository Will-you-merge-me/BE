package com.example.paymentserver.Service;

import com.example.paymentserver.Dto.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);

    PaymentDto findByPayment(Long userId);

    void refundPayment(Long paymentId);
}
