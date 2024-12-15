package com.lazarev.webhookservice.service;

import com.lazarev.webhookservice.dto.PaymentCreatedDto;
import com.lazarev.webhookservice.dto.PaymentDto;
import com.lazarev.webhookservice.entity.Payment;
import com.lazarev.webhookservice.entity.WebhookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentWebhookFacade {
    private final PaymentService paymentService;
    private final WebhookService webhookService;

    public PaymentDto getPaymentById(UUID id) {
        return paymentService.getPaymentById(id);
    }

    public PaymentCreatedDto createPayment(PaymentDto paymentDto) {
        WebhookClient webhookClient = webhookService.getWebhookClient(paymentDto.clientSystemCode());
        Payment payment = paymentService.createPayment(paymentDto, webhookClient);

        webhookService.notifyClient(payment);

        return new PaymentCreatedDto(payment.getId());
    }

    public void markPaymentAsSuccessful(UUID id) {
        Payment payment = paymentService.markPaymentAsSuccessful(id);
        webhookService.notifyClient(payment);
    }

    public void markPaymentAsFailed(UUID id) {
        Payment payment = paymentService.markPaymentAsFailed(id);
        webhookService.notifyClient(payment);
    }
}
