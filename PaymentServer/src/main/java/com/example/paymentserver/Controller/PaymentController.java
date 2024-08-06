package com.example.paymentserver.Controller;

import com.example.paymentserver.Dto.PaymentDto;
import com.example.paymentserver.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(@Autowired PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto){
        PaymentDto create = paymentService.createPayment(paymentDto);
        return ResponseEntity.ok().body(create);
    }
    @GetMapping("/find/{userId}")
    public ResponseEntity<PaymentDto> findPayment(@PathVariable Long userId){
        PaymentDto paymentDto = paymentService.findByPayment(userId);
        return ResponseEntity.ok().body(paymentDto);
    }
    @DeleteMapping("/refund/{paymentId}")
    public void refundPayment(@PathVariable Long paymentId){
        paymentService.refundPayment(paymentId);
    }
}
