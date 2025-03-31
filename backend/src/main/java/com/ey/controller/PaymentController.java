package com.ey.controller;

import com.ey.request.PaymentRequest;
import com.ey.response.PaymentResponse;
import com.ey.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public PaymentResponse makePayment(@RequestBody PaymentRequest request) {
        return paymentService.makePayment(request);
    }

    @GetMapping("/{paymentId}")
    public PaymentResponse getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }
}
