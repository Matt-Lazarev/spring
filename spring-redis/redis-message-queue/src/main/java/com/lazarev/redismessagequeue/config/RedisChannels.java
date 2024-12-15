package com.lazarev.redismessagequeue.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("redis.channels")
public class RedisChannels {
    private String chatChannel;
    private String roomChannel;
}
