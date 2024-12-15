package com.lazarev.webhookservice.dto;


import com.lazarev.webhookservice.entity.enums.PaymentStatus;
import java.util.Set;

public record WebhookClientDto(
        String name,
        String endpointUrl,
        Set<PaymentStatus> notificationStatuses
) { }
