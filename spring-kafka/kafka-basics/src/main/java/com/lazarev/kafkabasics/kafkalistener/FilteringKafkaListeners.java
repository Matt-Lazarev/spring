package com.lazarev.kafkabasics.kafkalistener;

import com.lazarev.kafkabasics.dto.MessageRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FilteringKafkaListeners {

    @KafkaListener(topics = "first", groupId = "consumer_group_1")
    public void listenAll(@Payload MessageRequest request) {
        System.out.println("listenAll: " + request.value());
    }

    @KafkaListener(topics = "first", groupId = "consumer_group_2",
            containerFactory = "listenerContainerFactory")
    public void listenFiltered(@Payload MessageRequest request) {
        System.out.println("listenFiltered: " + request.value());
    }
}
