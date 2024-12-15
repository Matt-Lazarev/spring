package com.lazarev.redismessagequeue.publisher;

import com.lazarev.redismessagequeue.config.RedisChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatPublisher {
    private final StringRedisTemplate redisTemplate;
    private final RedisChannels redisChannels;

    public void publish(String message){
        redisTemplate.convertAndSend(redisChannels.getChatChannel(), message);
    }
}
