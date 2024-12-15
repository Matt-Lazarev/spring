package com.lazarev.shortpolling.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("rest-clients.task-client")
public class TaskClientProperties {
    private String host;
    private String createTaskUrl;
    private String getTaskUrl;
}
