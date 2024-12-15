package com.lazarev.redismessagequeue.subscriber;

import com.lazarev.redismessagequeue.annotation.RedisListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@Slf4j
@RedisListener("${redis.channels.room-channel}")
public class RoomSubscriber implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("Consumed: {}", message);
    }
}
