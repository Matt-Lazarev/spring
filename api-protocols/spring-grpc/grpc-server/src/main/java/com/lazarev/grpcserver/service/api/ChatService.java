package com.lazarev.grpcserver.service.api;

import com.google.protobuf.Timestamp;
import com.lazarev.grpc.stubs.ChatMessage;
import com.lazarev.grpc.stubs.ChatServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.Instant;

@Slf4j
@GrpcService
public class ChatService extends ChatServiceGrpc.ChatServiceImplBase {

    @Override
    public StreamObserver<ChatMessage> chat(StreamObserver<ChatMessage> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(ChatMessage requestMessage) {
                log.info("Received message:\n{}", requestMessage);

                ChatMessage responseMessage = ChatMessage.newBuilder()
                        .setMessage("Response to: " + requestMessage.getMessage())
                        .setTimestamp(buildTimestamp())
                        .build();

                log.info("Response message:\n{}", responseMessage);

                responseObserver.onNext(responseMessage);
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Error while executing chat method: ", throwable);
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                log.info("Chat is ended");
                responseObserver.onCompleted();
            }
        };
    }

    private Timestamp buildTimestamp() {
        Instant now = Instant.now();
        return Timestamp.newBuilder()
                .setSeconds(now.getEpochSecond())
                .setNanos(now.getNano())
                .build();
    }
}
