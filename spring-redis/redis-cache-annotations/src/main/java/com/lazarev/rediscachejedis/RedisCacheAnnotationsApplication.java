package com.lazarev.rediscachejedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisCacheAnnotationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCacheAnnotationsApplication.class, args);
    }

}
