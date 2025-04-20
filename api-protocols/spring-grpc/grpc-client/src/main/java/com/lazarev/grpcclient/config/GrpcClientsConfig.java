package com.lazarev.grpcclient.config;

import com.lazarev.grpc.stubs.ChatServiceGrpc;
import com.lazarev.grpc.stubs.FileReceiverGrpc;
import com.lazarev.grpc.stubs.GreeterGrpc;
import com.lazarev.grpc.stubs.StockSenderGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientsConfig {
    @GrpcClient("greeter-client")
    private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

    @GrpcClient("stock-sender-client")
    private StockSenderGrpc.StockSenderBlockingStub stockSenderBlockingStub;

    @GrpcClient("stock-sender-client")
    private FileReceiverGrpc.FileReceiverStub fileReceiverStub;

    @GrpcClient("chat-service-client")
    private ChatServiceGrpc.ChatServiceStub chatServiceStub;

    @Bean
    public GreeterGrpc.GreeterBlockingStub greeterBlockingStub() {
        return greeterBlockingStub;
    }

    @Bean
    public StockSenderGrpc.StockSenderBlockingStub stockSenderBlockingStub() {
        return stockSenderBlockingStub;
    }

    @Bean
    public FileReceiverGrpc.FileReceiverStub fileReceiverStub() {
        return fileReceiverStub;
    }

    @Bean
    public ChatServiceGrpc.ChatServiceStub chatServiceStub() {
        return chatServiceStub;
    }
}
