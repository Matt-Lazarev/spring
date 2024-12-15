package com.lazarev.webhookservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazarev.webhookservice.entity.enums.Currency;
import com.lazarev.webhookservice.entity.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentDto(
        UUID id,
        BigDecimal amount,
        Currency currency,
        PaymentStatus status,
        String clientSystemCode
) { }
