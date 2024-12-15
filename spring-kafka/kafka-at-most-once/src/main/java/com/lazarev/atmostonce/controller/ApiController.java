package com.lazarev.atmostonce.controller;

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
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @PostMapping
    @SneakyThrows
    public void sendMessage(){
        for(int i=0; i<10; i++){
            kafkaTemplate.send(topic, "Test message " + i);
            Thread.sleep(500);
        }
    }
}
