package com.lazarev.personalaccountservice.dto;

public enum PaymentStatus {
    PENDING("Payment is accepted, processing"),
    SUCCESSFUL("Payment is successful"),
    FAILED("Payment is failed");

    private final String message;

    PaymentStatus(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
