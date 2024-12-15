package com.lazarev.springdataredis.dto;

import java.util.List;
import java.util.UUID;

public record ArticleInfoDto (
        UUID id,
        String name,
        AuthorDto author,
        List<CommentDto> comments) {
}
