package com.lazarev.kafkajsonproducer.config;

import com.lazarev.model.OrderMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ProducerConfiguration {
    private final KafkaProperties kafkaProperties;

    @Bean
    public KafkaTemplate<Integer, OrderMessage> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<Integer, OrderMessage> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    private Map<String, Object> producerConfig(){
        return kafkaProperties.buildProducerProperties();
    }
}
