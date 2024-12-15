package com.lazarev.webhookservice.controller;

import com.lazarev.webhookservice.dto.PaymentCreatedDto;
import com.lazarev.webhookservice.dto.PaymentDto;
import com.lazarev.webhookservice.service.PaymentWebhookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentWebhookFacade paymentWebhookFacade;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable UUID id) {
        PaymentDto payment = paymentWebhookFacade.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<PaymentCreatedDto> createPayment(@RequestBody PaymentDto paymentDto) {
        PaymentCreatedDto paymentCreatedDto = paymentWebhookFacade.createPayment(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentCreatedDto);
    }

    @PutMapping("/{id}/success")
    public ResponseEntity<Void> markPaymentAsSuccessful(@PathVariable UUID id) {
        paymentWebhookFacade.markPaymentAsSuccessful(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/fail")
    public ResponseEntity<Void> markPaymentAsFailed(@PathVariable UUID id) {
        paymentWebhookFacade.markPaymentAsFailed(id);
        return ResponseEntity.ok().build();
    }
}
