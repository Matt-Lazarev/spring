package com.lazarev.shortpolling.service;

import com.lazarev.shortpolling.client.TaskClient;
import com.lazarev.shortpolling.dto.FileTaskRequest;
import com.lazarev.shortpolling.dto.TaskRequest;
import com.lazarev.shortpolling.dto.TaskResponse;
import com.lazarev.shortpolling.entity.Task;
import com.lazarev.shortpolling.mapper.TaskMapper;
import com.lazarev.shortpolling.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileTaskService {
    private final TaskRepository taskRepository;
    private final TaskClient taskClient;
    private final TaskMapper taskMapper;

    public TaskResponse createFileParseTask(FileTaskRequest fileTaskRequest) {
        TaskRequest taskRequest = taskMapper.toTaskRequest(fileTaskRequest);
        TaskResponse createdTask = taskClient.createTask(taskRequest);

        Task task = taskMapper.toTask(createdTask);
        taskRepository.save(task);

        return createdTask;
    }
}
