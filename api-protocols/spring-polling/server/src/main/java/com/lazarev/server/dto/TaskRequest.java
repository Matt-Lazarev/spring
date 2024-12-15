package com.lazarev.server.dto;

public record TaskRequest(
        String name,
        Integer millis,
        boolean async
) { }
