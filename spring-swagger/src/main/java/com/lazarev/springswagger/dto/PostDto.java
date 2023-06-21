package com.lazarev.springswagger.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for post request")
public record PostDto (
        @Schema(description = "Информация", example = "info")
        String info
) {}
