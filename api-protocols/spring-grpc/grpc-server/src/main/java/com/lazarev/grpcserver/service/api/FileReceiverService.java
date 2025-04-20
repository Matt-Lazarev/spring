package com.lazarev.grpcserver.service.api;

import com.lazarev.grpc.stubs.File;
import com.lazarev.grpc.stubs.FileReceiverGrpc;
import com.lazarev.grpc.stubs.FilesInfo;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class FileReceiverService extends FileReceiverGrpc.FileReceiverImplBase {
    @Override
    public StreamObserver<File> getFilesInfo(StreamObserver<FilesInfo> responseObserver) {
        return new StreamObserver<>() {
            private int filesCount = 0;
            private long filesTotalSize = 0;

            @Override
            public void onNext(File file) {
                log.info("Received file from client: {}", file);

                if (file.getSize() > 5000) {
                    responseObserver.onError(
                            Status.INVALID_ARGUMENT
                                    .withDescription("File size exceeds the maximum limit of 5000 bytes")
                                    .asRuntimeException()
                    );
                    return;
                }

                filesCount++;
                filesTotalSize += file.getSize();
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Got an error while executing getFilesInfo:", throwable);
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                FilesInfo filesInfo = FilesInfo.newBuilder()
                        .setCount(filesCount)
                        .setTotalSize(filesTotalSize)
                        .build();

                log.info("getFilesInfo executed successfully with result: {} ", filesInfo);

                responseObserver.onNext(filesInfo);
                responseObserver.onCompleted();
            }
        };
    }
}
