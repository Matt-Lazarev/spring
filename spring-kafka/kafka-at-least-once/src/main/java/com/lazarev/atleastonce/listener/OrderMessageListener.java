package com.lazarev.atleastonce.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
public class OrderMessageListener {

    @SneakyThrows
    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void listenOrderMessage(ConsumerRecord<String, String> record){
        log.info("received: {}, key={}, partition={}", record.value(), record.key(), record.partition());
        Thread.sleep(2000);
        log.info("processed: {}, key={}, partition={}", record.value(), record.key(), record.partition());
    }
}
