package com.lazarev.kafkajsonproducer.dto;

public record OrderMessage
        (Integer id, Integer type, String text){ }
