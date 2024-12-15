package com.lazarev.shortpolling.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@RequiredArgsConstructor
public final class TaskResponse {
    private final Integer id;
    private String name;
    private boolean ready;
    private String message;
}
