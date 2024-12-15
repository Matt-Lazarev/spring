package com.lazarev.kafkajsonconsumer.listener;

import com.lazarev.model.OrderMessage;
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
    public void listenOrderMessage(ConsumerRecord<Integer, OrderMessage> record){
        log.info("received: {}, key={}, partition={}", record.value(), record.key(), record.partition());
        int type = record.value().type();
        if(type < 0 || type > 2){
            throw new IllegalArgumentException("Wrong order-type: %d".formatted(type));
        }
        log.info("processed: {}, key={}, partition={}", record.value(), record.key(), record.partition());
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}_dlt")
    public void listenOrderMessageDlt(ConsumerRecord<Integer, OrderMessage> record){
        log.info("received in DLT: {}, key={}, partition={}", record.value(), record.key(), record.partition());
    }
}
