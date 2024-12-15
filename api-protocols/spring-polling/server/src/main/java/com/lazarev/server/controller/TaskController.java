package com.lazarev.server.controller;

import com.lazarev.server.dto.TaskRequest;
import com.lazarev.server.dto.TaskResponse;
import com.lazarev.server.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskResponse submitTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }
}
