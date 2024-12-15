package com.lazarev.kafkabasics.kafkalistener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaListeners {

    @KafkaListener(topics = "first", groupId = "consumer_group_1")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Value: " + record.value());
        System.out.println("Key: " + record.key());
        System.out.println("Partition: " + record.partition());
        System.out.println("Topic: " + record.topic());
        System.out.println("Timestamp: " + record.timestamp());
    }
}
