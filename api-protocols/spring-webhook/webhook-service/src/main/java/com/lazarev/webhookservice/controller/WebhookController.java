package com.lazarev.webhookservice.controller;

import com.lazarev.webhookservice.dto.WebhookClientDto;
import com.lazarev.webhookservice.service.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class WebhookController {
    private final WebhookService webhookService;

    @PostMapping
    public ResponseEntity<Void> registerWebhook(@RequestBody WebhookClientDto webhookClientDto) {
        webhookService.createWebhook(webhookClientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
