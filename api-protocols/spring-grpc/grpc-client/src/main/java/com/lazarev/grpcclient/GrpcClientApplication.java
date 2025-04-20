package com.lazarev.grpcclient;

import com.google.protobuf.Timestamp;
import com.lazarev.grpc.stubs.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Iterator;

@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            GreeterGrpc.GreeterBlockingStub greeterGrpcClient,
            StockSenderGrpc.StockSenderBlockingStub stockSenderBlockingStub,
            FileReceiverGrpc.FileReceiverStub fileReceiverStub,
            ChatServiceGrpc.ChatServiceStub chatServiceStub
    ) {
        return args -> {
            simpleApi(greeterGrpcClient);
            serverSideStreaming(stockSenderBlockingStub);
            clientSideStreaming(fileReceiverStub);
            bidirectionalStreaming(chatServiceStub);
        };
    }

    private void simpleApi(GreeterGrpc.GreeterBlockingStub greeterGrpcClient) {
        System.out.println("-------- Simple API --------");

        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setName("Matt")
                .build();

        HelloResponse helloResponse = greeterGrpcClient.sayHello(helloRequest);
        System.out.println(helloResponse);
    }

    private void serverSideStreaming(StockSenderGrpc.StockSenderBlockingStub stockSenderBlockingStub) {
        System.out.println("\n-------- Server-Side streaming --------");

        GetStocksRequest getStocksRequest = GetStocksRequest.newBuilder()
                .setNumber(10)
                .build();

        Iterator<Stock> stocks = stockSenderBlockingStub.getStocks(getStocksRequest);
        stocks.forEachRemaining(s -> System.out.println(s.getName() + ":\n" + s.getMoney()));
    }

    private void clientSideStreaming(FileReceiverGrpc.FileReceiverStub fileReceiverStub) {
        System.out.println("\n-------- Client-Side streaming --------");

        StreamObserver<File> requestObserver = fileReceiverStub.getFilesInfo(
                new StreamObserver<>() {
                    @Override
                    public void onNext(FilesInfo filesInfo) {
                        System.out.println("Received FilesInfo from the server:\n" + filesInfo);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("getFilesInfo completed with an error: " + throwable);
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("getFilesInfo successfully completed");
                    }
                }
        );

        for (int i = 0; i < 10; i++) {
            File file = File.newBuilder()
                    .setName("file-%d.txt".formatted(i))
                    .setSize(100)
                    .build();

            if(i == 5) {
                Throwable error = Status.INVALID_ARGUMENT.withDescription("Invalid message format").asRuntimeException();
                requestObserver.onError(error);
                return;
            }

            requestObserver.onNext(file);
        }
        requestObserver.onCompleted();
    }

    private void bidirectionalStreaming(ChatServiceGrpc.ChatServiceStub chatServiceStub) {
        System.out.println("\n-------- Bidirectional streaming --------");

        StreamObserver<ChatMessage> requestObserver = chatServiceStub.chat(
                new StreamObserver<>() {
                    @Override
                    public void onNext(ChatMessage chatMessage) {
                        System.out.println("Received a ChatMessage from the server:\n" + chatMessage);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("chat method completed with an error: " + throwable);
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("chat method successfully completed");
                    }
                }
        );

        for (int i = 1; i <= 5; i++) {
            Instant now = Instant.now();
            ChatMessage requestMessage = ChatMessage.newBuilder()
                    .setMessage("Hello %d!".formatted(i))
                    .setTimestamp(Timestamp.newBuilder()
                            .setSeconds(now.getEpochSecond())
                            .setNanos(now.getNano())
                            .build())
                    .build();

            requestObserver.onNext(requestMessage);
        }
        requestObserver.onCompleted();
    }
}
