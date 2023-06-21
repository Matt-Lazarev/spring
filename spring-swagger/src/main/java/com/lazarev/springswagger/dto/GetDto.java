package com.lazarev.springswagger.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for get request")
public record GetDto (
        @Schema(description = "Информация", example = "info")
        String info
) {}
