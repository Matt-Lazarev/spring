package com.lazarev.kafkabasics.config.consumer;

import com.lazarev.kafkabasics.dto.MessageRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class FilteringKafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
    public ConsumerFactory<String, MessageRequest> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageRequest> listenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRecordInterceptor(recordInterceptor());
        factory.setRecordFilterStrategy(consumerRecord -> {
            MessageRequest request = consumerRecord.value();
            return !request.value().matches("\\w+");
        });
        return factory;
    }

    @Bean
    public RecordInterceptor<String, MessageRequest> recordInterceptor() {
        return record -> {
            System.out.println("--intercepted--");
            return record;
        };
    }

    @Bean
    public KafkaListenerErrorHandler validationErrorHandler() {
        return (m, e) -> {
            System.out.println(e.getMessage());
            return m;
        };
    }

    private Map<String, Object> consumerConfig() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset,
                JsonDeserializer.TRUSTED_PACKAGES, "*",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    }
}
