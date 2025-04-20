package com.lazarev.springwebsocket.controller;

import com.lazarev.springwebsocket.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    // for sending: stompClient.send('/app/message')
    @MessageMapping("/message")
    // for subscribe: stompClient.subscribe('/topic/messages')
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        return message;
    }

    @GetMapping("/chat")
    public String getChatPage() {
        return "chat";
    }
}
