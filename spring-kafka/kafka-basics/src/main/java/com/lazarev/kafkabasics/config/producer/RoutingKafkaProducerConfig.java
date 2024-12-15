package com.lazarev.kafkabasics.config.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.RoutingKafkaTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

//@Configuration
public class RoutingKafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, String> stringProducerFactory(){
        DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(producerConfig());
        factory.setProducerPerThread(true);
        return factory;
    }

    @Bean
    public ProducerFactory<String, Integer> integerProducerFactory(){
        DefaultKafkaProducerFactory<String, Integer> factory = new DefaultKafkaProducerFactory<>(producerConfig());
        factory.setProducerPerThread(true);
        return factory;
    }

    @Bean
    @SuppressWarnings("all")
    public RoutingKafkaTemplate routingKafkaTemplate(){
        Map<Pattern, ProducerFactory<Object, Object>> map = new LinkedHashMap<>();
        map.put(Pattern.compile("first"), (ProducerFactory) stringProducerFactory());
        map.put(Pattern.compile("second"), (ProducerFactory) integerProducerFactory());
        return new RoutingKafkaTemplate(map);
    }

    private Map<String, Object> producerConfig(){
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    }
}

