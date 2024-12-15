package com.lazarev.kafkajsonconsumer.config;

import com.lazarev.model.OrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.*;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ConsumerConfiguration {
    private final KafkaProperties kafkaProperties;
    private final KafkaOperations<Object, Object> kafkaTemplate;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, OrderMessage>> containerFactory(){
        ConcurrentKafkaListenerContainerFactory<Integer, OrderMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setCommonErrorHandler(deadLetterErrorHandler());
        factory.getContainerProperties().setPollTimeout(15_000);
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, OrderMessage> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public DefaultErrorHandler deadLetterErrorHandler(){
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
                kafkaTemplate, (record, ex) -> new TopicPartition("t_messages_dlt", 0));
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                recoverer, new FixedBackOff(2000, 1));
        errorHandler.addNotRetryableExceptions(IllegalArgumentException.class);
        return errorHandler;
    }

    private Map<String, Object> consumerConfig(){
        return kafkaProperties.buildConsumerProperties();
    }
}
