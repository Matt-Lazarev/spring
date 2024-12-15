package com.lazarev.shortpolling.mapper;

import com.lazarev.shortpolling.dto.FileTaskRequest;
import com.lazarev.shortpolling.dto.TaskRequest;
import com.lazarev.shortpolling.dto.TaskResponse;
import com.lazarev.shortpolling.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskResponse taskResponse);

    @Mapping(target = "async", constant = "false")
    TaskRequest toTaskRequest(FileTaskRequest fileTaskRequest);
}
