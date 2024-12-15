package com.lazarev.shortpolling.dto;

public record TaskRequest(String name, Integer millis, boolean async) { }
