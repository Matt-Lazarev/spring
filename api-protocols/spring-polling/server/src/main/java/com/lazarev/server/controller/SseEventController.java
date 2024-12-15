package com.lazarev.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class SseEventController {

    @GetMapping("/sse-stream")
    public SseEmitter streamEvents() {
        SseEmitter emitter = new SseEmitter();
        AtomicInteger counter = new AtomicInteger();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .id(String.valueOf(counter.incrementAndGet()))
                        .name("sse event - mvc")
                        .data("SSE MVC - " + LocalTime.now());
                emitter.send(event);
            }
            catch (IOException ex) {
                emitter.completeWithError(ex);
            }
        }, 0, 1, TimeUnit.SECONDS);
        return emitter;
    }
}
