package com.lazarev.kafkabasics.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record MessageRequest(
        String topic,
        String key,

        @NotNull(message = "Payload cannot be null")
        @NotBlank(message = "Payload cannot be empty or blank")
        String value) { }
