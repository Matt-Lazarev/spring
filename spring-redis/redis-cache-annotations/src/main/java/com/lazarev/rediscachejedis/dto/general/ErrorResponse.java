package com.lazarev.rediscachejedis.dto.general;

public record ErrorResponse (int statusCode, String error, String message) { }
