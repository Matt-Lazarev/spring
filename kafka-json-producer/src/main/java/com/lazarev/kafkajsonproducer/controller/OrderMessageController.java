package com.lazarev.kafkajsonproducer.controller;

import com.lazarev.kafkajsonproducer.dto.OrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-messages")
public class OrderMessageController {
    @Value("${spring.kafka.template.default-topic}")
    private String defaultTopic;

    private final KafkaTemplate<Integer, OrderMessage> kafkaTemplate;

    @PostMapping
    public ResponseEntity<?> publishMessage(@RequestBody OrderMessage orderMessage){
        kafkaTemplate.send(defaultTopic, orderMessage.type(), orderMessage);
        log.info("message with id='{}', key='{}', text='{}' has sent to topic '{}'",
                orderMessage.id(), orderMessage.type(), orderMessage.text(), defaultTopic);
        return ResponseEntity.ok().build();
    }
}
