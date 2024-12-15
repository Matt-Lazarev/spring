package com.lazarev.model;

public record OrderMessage(
        Integer id,
        Integer type,
        String text
) { }
