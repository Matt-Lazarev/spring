package com.lazarev.exactlyonce.controller;

import com.lazarev.exactlyonce.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;

    @PostMapping
    public void sendMessage(){
        apiService.sendMessages();
    }
}
