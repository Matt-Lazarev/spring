package com.lazarev.springdataredis.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Data
@RedisHash("comments")
public class Comment {
    @Id
    private UUID id;
    private String text;
}
