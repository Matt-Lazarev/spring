package com.lazarev.shortpolling.service;

import com.lazarev.shortpolling.client.TaskClient;
import com.lazarev.shortpolling.dto.FileTaskRequest;
import com.lazarev.shortpolling.dto.TaskRequest;
import com.lazarev.shortpolling.dto.TaskResponse;
import com.lazarev.shortpolling.entity.Task;
import com.lazarev.shortpolling.entity.TaskStatus;
import com.lazarev.shortpolling.mapper.TaskMapper;
import com.lazarev.shortpolling.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return new TaskResponse(task.getId())
                .setMessage(task.getMessage());
    }

    public void updateFileParseTaskStatuses() {
        List<Task> notFinishedTasks = taskRepository.findAllByStatusIn(TaskStatus.NOT_FINISHED_STATUSES);

        log.info("Not finished parse-file tasks: {}", notFinishedTasks.size());

        notFinishedTasks.forEach(task -> {
            TaskResponse taskResponse = taskClient.getTask(task.getId());
            TaskStatus newStatus = taskResponse.isReady() ? TaskStatus.FINISHED : TaskStatus.PENDING;

            logTaskStatusChange(task, newStatus);

            task.setStatus(newStatus);
            task.setMessage(taskResponse.getMessage());
        });
        taskRepository.saveAll(notFinishedTasks);
    }

    private void logTaskStatusChange(Task task, TaskStatus newStatus) {
        if(task.getStatus() != newStatus) {
            log.info(
                    "Status of task with id = {} has changed from '{}' to '{}'",
                    task.getId(), task.getStatus(), newStatus
            );
        }
    }
}
