package com.lazarev.personalaccountservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentNotification(
        UUID paymentId,
        PaymentStatus newStatus,
        String message
) { }
