package com.lazarev.springdataredis.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@RedisHash(value = "articles", timeToLive = 60)
public class Article {
    @Id
    private UUID id;

    @Indexed
    private String name;

    private Author author;

    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
