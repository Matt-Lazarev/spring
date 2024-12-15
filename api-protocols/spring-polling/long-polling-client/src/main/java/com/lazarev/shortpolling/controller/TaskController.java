package com.lazarev.shortpolling.controller;

import com.lazarev.shortpolling.dto.FileTaskRequest;
import com.lazarev.shortpolling.dto.TaskRequest;
import com.lazarev.shortpolling.dto.TaskResponse;
import com.lazarev.shortpolling.service.FileTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class TaskController {
    private final FileTaskService fileTaskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createParseFileTask(@RequestBody FileTaskRequest fileTaskRequest) {
        TaskResponse taskResponse = fileTaskService.createFileParseTask(fileTaskRequest);
        return ResponseEntity.ok(taskResponse);
    }
}
