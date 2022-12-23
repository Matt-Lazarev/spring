package com.lazarev.kafkabasics.kafkalistener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

//@Component
public class ReplyingKafkaListeners {

    @SendTo
    @KafkaListener(topics={"first"}, groupId = "consumer_group_1")
    public String firstTopicListener(String data){
        System.out.println("Received from first topic: " + data);
        return data.toUpperCase();
    }

    @SendTo
    @KafkaListener(topics={"second"}, groupId = "consumer_group_1")
    public String secondTopicListener(String data){
        System.out.println("Received from second topic: " + data);
        return data.toUpperCase();
    }

    @KafkaListener(topics={"reply"}, groupId = "consumer_group_3")
    public void replyTopicListener(String data){
        System.out.println("Reply received: " + data);
    }
}
