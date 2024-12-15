package com.lazarev.webhookservice.dto;

import com.lazarev.webhookservice.entity.enums.PaymentStatus;

import java.util.UUID;

public record WebhookNotification(
        UUID paymentId,
        PaymentStatus newStatus,
        String message,
        String endpointUrl
) { }
