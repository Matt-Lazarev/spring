package com.lazarev.webhookservice.service;

import com.lazarev.webhookservice.client.WebhookNotificationClient;
import com.lazarev.webhookservice.dto.WebhookClientDto;
import com.lazarev.webhookservice.dto.WebhookNotification;
import com.lazarev.webhookservice.entity.Payment;
import com.lazarev.webhookservice.entity.WebhookClient;
import com.lazarev.webhookservice.entity.enums.PaymentStatus;
import com.lazarev.webhookservice.mapper.PaymentWebhookMapper;
import com.lazarev.webhookservice.repository.WebhookClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WebhookService {
    private final WebhookClientRepository webhookClientRepository;
    private final WebhookNotificationClient webhookNotificationClient;
    private final PaymentWebhookMapper webhookMapper;

    public WebhookClient getWebhookClient(String clientName) {
        return findWebhookClientByName(clientName);
    }

    public void createWebhook(WebhookClientDto webhookClientDto) {
        WebhookClient webhookClient = webhookMapper.toWebhookClient(webhookClientDto);
        webhookClientRepository.save(webhookClient);
    }

    public void notifyClient(Payment payment) {
        Set<PaymentStatus> notificationStatuses = payment.getWebhookClient().getNotificationStatuses();
        if(notificationStatuses.contains(payment.getStatus())) {
            WebhookNotification webhookNotification = webhookMapper.toWebhookNotification(payment);
            webhookNotificationClient.notifyClient(webhookNotification);
        }
    }

    private WebhookClient findWebhookClientByName(String name) {
        return webhookClientRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(
                        "WebhookClient with name = %s not found".formatted(name))
                );
    }
}
