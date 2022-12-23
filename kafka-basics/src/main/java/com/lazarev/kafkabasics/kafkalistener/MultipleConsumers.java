package com.lazarev.kafkabasics.kafkalistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class MultipleConsumers {

    @KafkaListener(
            groupId = "consumer_multipart", concurrency = "3",
            topicPartitions = @TopicPartition(topic = "t_multipart", partitions = "0,1,2"))
    public void consumeAllPartitions(String data) throws InterruptedException {
        log.info("Consumed in partition 0-2 : {}", data);
        Thread.sleep(1500);
    }


}
