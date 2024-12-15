package com.lazarev.kafkabasics.controller;

import com.lazarev.kafkabasics.dto.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final KafkaTemplate<String, MessageRequest> messageKafkaTemplate;


    @PostMapping("/string")
    public ResponseEntity<?> publishString(@RequestBody MessageRequest request) {
        stringKafkaTemplate.send(request.topic(), request.key(), request.value());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/message")
    public ResponseEntity<?> publishMessage(@RequestBody MessageRequest request) {
        messageKafkaTemplate.send(request.topic(), request.key(), request);
        return ResponseEntity.ok().build();
    }
}
