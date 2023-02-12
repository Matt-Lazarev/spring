package com.lazarev.kafkajsonconsumer.listener;

import com.lazarev.kafkajsonproducer.dto.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
public class OrderMessageListener {
    @KafkaListener(topics = "${spring.kafka.template.default-topic}",
            groupId = "messages-group-1", containerFactory = "containerFactory")
    public void listenOrderMessage(ConsumerRecord<String, OrderMessage> record){
        int type = record.value().type();
        if(type < 0 || type > 2){
            throw new IllegalArgumentException("Wrong order-type: %d".formatted(type));
        }
        log.info("received: {}, key={}, partition={}", record.value(), record.key(), record.partition());
    }
}
