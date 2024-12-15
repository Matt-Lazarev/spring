package com.lazarev.kafkabasics.config.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.util.Map;

//@Configuration
public class ReplyingKafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, String> stringProducerFactory(){
        DefaultKafkaProducerFactory<String, String> factory =
                new DefaultKafkaProducerFactory<>(producerConfig());
        factory.setProducerPerThread(true);
        return factory;
    }

    @Bean
    public KafkaTemplate<String, String> stringKafkaTemplate(){
        return new KafkaTemplate<>(stringProducerFactory());
    }

    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(
            ConcurrentMessageListenerContainer<String, String> messageListenerContainer) {
        return new ReplyingKafkaTemplate<>(stringProducerFactory(), messageListenerContainer);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, String> messageListenerContainer(
            ConcurrentKafkaListenerContainerFactory<String, String> factory){
        factory.setReplyTemplate(stringKafkaTemplate());
        ConcurrentMessageListenerContainer<String, String> container =
                factory.createContainer("reply");
        container.getContainerProperties().setGroupId("reply-container");
        return container;
    }



    private Map<String, Object> producerConfig(){
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    }
}
