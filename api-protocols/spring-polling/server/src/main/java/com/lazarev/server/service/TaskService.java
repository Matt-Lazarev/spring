package com.lazarev.server.service;

import com.lazarev.server.model.Task;
import com.lazarev.server.dto.TaskRequest;
import com.lazarev.server.dto.TaskResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {
    private final Map<Integer, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    public TaskResponse createTask(TaskRequest taskRequest) {
        int taskId = counter.incrementAndGet();
        Task task = new Task(taskId, taskRequest.name(), false);
        tasks.put(taskId, task);

        CompletableFuture<Task> taskPromise = performTask(taskId, taskRequest);
        return handleTask(taskPromise, task, taskRequest.async());
    }

    public TaskResponse getTaskById(Integer id) {
        Task task = tasks.get(id);
        String message = getTaskMessage(task);
        return new TaskResponse(id, task.getName(), task.isReady(), message);
    }

    private CompletableFuture<Task> performTask(int taskId, TaskRequest taskRequest) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(taskRequest.millis());
                return tasks.computeIfPresent(taskId, (id, task) -> {
                    task.setReady();
                    return task;
                });
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private String getTaskMessage(Task task) {
        return task.isReady() ? "Task has completed" : "Task is in progress";
    }

    @SneakyThrows
    private TaskResponse handleTask(CompletableFuture<Task> taskPromise, Task notReadyTask, boolean async) {
        if(async) {
            return new TaskResponse(notReadyTask.getId(), notReadyTask.getName(), false, "Task has created");
        }
        Task readyTask = taskPromise.get();
        return new TaskResponse(readyTask.getId(), readyTask.getName(), true, getTaskMessage(readyTask));
    }
}
