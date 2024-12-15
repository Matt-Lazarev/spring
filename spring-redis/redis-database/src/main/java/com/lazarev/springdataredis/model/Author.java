package com.lazarev.springdataredis.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Data
@RedisHash("authors")
public class Author {
    @Id
    private UUID id;
    private String name;
}
