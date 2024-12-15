package com.lazarev.redismessagequeue.subscriber;

import com.lazarev.redismessagequeue.annotation.RedisListener;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@Slf4j
@RedisListener("${redis.channels.chat-channel}")
public class ChatSubscriber implements MessageListener {
    @Override
    public void onMessage(@NonNull Message message, byte[] pattern) {
        log.info("Consumed: {}", message);
    }
}
