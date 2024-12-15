package com.lazarev.atmostonce.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

@Slf4j
@Configuration
public class OrderMessageListener {

    @SneakyThrows
    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void listenOrderMessage(ConsumerRecord<String, String> record, Acknowledgment ack){
        ack.acknowledge();

        log.info("received: {}, key={}, partition={}", record.value(), record.key(), record.partition());
        Thread.sleep(2000);
        log.info("processed: {}, key={}, partition={}", record.value(), record.key(), record.partition());
    }
}
