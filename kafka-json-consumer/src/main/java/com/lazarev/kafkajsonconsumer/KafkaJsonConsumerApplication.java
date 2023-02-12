package com.lazarev.kafkajsonconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.lazarev.kafkajsonconsumer", "com.lazarev.kafkajsonproducer"
})
public class KafkaJsonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaJsonConsumerApplication.class, args);
    }

}
