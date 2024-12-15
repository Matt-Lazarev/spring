package com.lazarev.springdataredis.dto;

import java.util.UUID;

public record CommentDto(UUID id, String text) {
}
