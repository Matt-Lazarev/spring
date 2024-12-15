package com.lazarev.springhttp2.controller;

import com.lazarev.springhttp2.dto.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/api/message")
    public Message getMessage() {
        return new Message(1, "Text message");
    }
}
