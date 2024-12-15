package com.lazarev.springdataredis.dto;

import java.util.UUID;

public record ArticleDto (UUID id, String name, UUID authorId) {}
