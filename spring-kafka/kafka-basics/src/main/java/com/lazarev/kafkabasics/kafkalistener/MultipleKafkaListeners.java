package com.lazarev.kafkabasics.kafkalistener;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener(id = "multi", topics = "myTopic")
public class MultipleKafkaListeners {
    @KafkaHandler
    public void listen(String data) {
        System.out.println(data);
    }

    @KafkaHandler(isDefault = true)
    public void listenDefault(Object data) {
        System.out.println(data);
    }
}

