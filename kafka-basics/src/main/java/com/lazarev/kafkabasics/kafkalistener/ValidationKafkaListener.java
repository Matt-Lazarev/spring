package com.lazarev.kafkabasics.kafkalistener;

import com.lazarev.kafkabasics.dto.MessageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

//@Component
public class ValidationKafkaListener {

    @KafkaListener(topics={"second"}, groupId = "consumer_group_2",
            containerFactory = "listenerContainerFactory",
            errorHandler = "validationErrorHandler")
    public void secondTopicListener(@Valid @Payload MessageRequest data){
        System.out.println("Received from second topic: " + data);
    }
}
