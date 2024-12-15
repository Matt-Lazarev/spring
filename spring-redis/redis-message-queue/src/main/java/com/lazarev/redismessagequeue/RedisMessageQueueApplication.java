package com.lazarev.redismessagequeue;

import com.lazarev.redismessagequeue.publisher.ChatPublisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisMessageQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisMessageQueueApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ChatPublisher chatPublisher){
        return args -> {
            chatPublisher.publish("Hello!");
            chatPublisher.publish("World!");
        };
    }
}
