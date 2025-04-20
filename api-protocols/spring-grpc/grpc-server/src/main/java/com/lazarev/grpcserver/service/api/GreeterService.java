package com.lazarev.grpcserver.service.api;

import com.lazarev.grpc.stubs.GreeterGrpc;
import com.lazarev.grpc.stubs.HelloRequest;
import com.lazarev.grpc.stubs.HelloResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreeterService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse helloResponse = HelloResponse.newBuilder()
                .setMessage("Hello %s!".formatted(request.getName()))
                .build();

        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }
}
