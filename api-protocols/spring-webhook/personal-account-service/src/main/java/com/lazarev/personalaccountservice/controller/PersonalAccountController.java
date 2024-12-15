package com.lazarev.personalaccountservice.controller;

import com.lazarev.personalaccountservice.dto.PaymentNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/payment")
public class PersonalAccountController {

    @PostMapping
    public ResponseEntity<Void> handlePaymentNotification(@RequestBody PaymentNotification event) {
        log.info("Consumed payment notification: {}", event);
        return ResponseEntity.ok().build();
    }
}
