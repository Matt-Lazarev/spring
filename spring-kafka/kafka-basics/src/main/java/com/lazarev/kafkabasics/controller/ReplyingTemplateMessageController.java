package com.lazarev.kafkabasics.controller;

import com.lazarev.kafkabasics.dto.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyMessageFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/api/v1/reply-messages")
@RequiredArgsConstructor
public class ReplyingTemplateMessageController {
    private final ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

    @PostMapping
    public ResponseEntity<?> publish(@RequestBody MessageRequest request) throws Exception {
        RequestReplyMessageFuture<String, String> reply =
                kafkaTemplate.sendAndReceive(createMessage(request));
        return ResponseEntity.ok(reply.get());
    }

    private Message<String> createMessage(MessageRequest request){
        return MessageBuilder
                .withPayload(request.value())
                .setHeader(KafkaHeaders.TOPIC, request.topic())
                .setHeader(KafkaHeaders.MESSAGE_KEY, "key")
                .setHeader("X-Custom-Header", "header-value")
                .build();
    }
}
