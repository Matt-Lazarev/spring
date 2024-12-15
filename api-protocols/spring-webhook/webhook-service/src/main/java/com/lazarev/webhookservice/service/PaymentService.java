package com.lazarev.webhookservice.service;

import com.lazarev.webhookservice.dto.PaymentDto;
import com.lazarev.webhookservice.entity.Payment;
import com.lazarev.webhookservice.entity.WebhookClient;
import com.lazarev.webhookservice.entity.enums.PaymentStatus;
import com.lazarev.webhookservice.mapper.PaymentWebhookMapper;
import com.lazarev.webhookservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentWebhookMapper paymentWebhookMapper;

    public PaymentDto getPaymentById(UUID id) {
        Payment payment = findPaymentById(id);
        return paymentWebhookMapper.toPaymentDto(payment);
    }

    public Payment createPayment(PaymentDto paymentDto, WebhookClient webhookClient) {
        Payment payment = paymentWebhookMapper.toPayment(paymentDto, webhookClient);
        return paymentRepository.save(payment);
    }

    public Payment markPaymentAsSuccessful(UUID id) {
        return updatePaymentStatus(id, PaymentStatus.SUCCESSFUL);
    }

    public Payment markPaymentAsFailed(UUID id) {
        return updatePaymentStatus(id, PaymentStatus.FAILED);
    }

    private Payment updatePaymentStatus(UUID id, PaymentStatus newPaymentStatus) {
        Payment payment = findPaymentById(id);
        payment.setStatus(newPaymentStatus);

        return paymentRepository.save(payment);
    }

    private Payment findPaymentById(UUID id) {
        return paymentRepository.findPaymentById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment with id = '%s' not found".formatted(id)));
    }
}
