package com.lazarev.shortpolling.client;

import com.lazarev.shortpolling.config.TaskClientProperties;
import com.lazarev.shortpolling.dto.TaskRequest;
import com.lazarev.shortpolling.dto.TaskResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskClient {
    private final RestClient restClient;
    private final TaskClientProperties taskClientProperties;

    public TaskResponse createTask(TaskRequest taskRequest) {
        try {
            return restClient.post()
                    .uri(taskClientProperties.getHost() + taskClientProperties.getCreateTaskUrl())
                    .contentType(APPLICATION_JSON)
                    .body(taskRequest)
                    .retrieve()
                    .body(TaskResponse.class);
        } catch (Exception ex) {
            log.error("Error while creating a task {}", taskRequest);
            throw new RuntimeException(ex);
        }
    }

    public TaskResponse getTask(Integer id) {
        try {
            return restClient.get()
                    .uri(taskClientProperties.getHost() + taskClientProperties.getGetTaskUrl(), id)
                    .retrieve()
                    .body(TaskResponse.class);
        } catch (Exception ex) {
            log.error("Error while getting a task with id = {}", id);
            throw new RuntimeException(ex);
        }
    }
}
