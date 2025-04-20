package com.lazarev.springwebsocket.dto;

public record ChatMessage(
        String sender,
        String content
) { }
