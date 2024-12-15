package com.lazarev.exactlyonce.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @SneakyThrows
    @Transactional
    public void sendMessages(){
        for(int i=0; i<10; i++){
            kafkaTemplate.send(topic, "Test message " + i);
            Thread.sleep(1000);
            log.info("Sent message to Kafka: " + i);
        }
    }
}
