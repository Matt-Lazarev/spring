package com.lazarev.shortpolling.scheduler;

import com.lazarev.shortpolling.service.FileTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckTaskStatusScheduler {
    private final FileTaskService fileTaskService;

    @Scheduled(cron = "${scheduler.task-status-checker}")
    public void checkTaskStatus() {
        log.info("Start checking parse file tasks");

        fileTaskService.updateFileParseTaskStatuses();

        log.info("Finish checking parse file tasks");
    }
}
