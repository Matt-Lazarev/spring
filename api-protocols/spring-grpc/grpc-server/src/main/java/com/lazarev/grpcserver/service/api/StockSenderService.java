package com.lazarev.grpcserver.service.api;

import com.google.type.Money;
import com.lazarev.grpc.stubs.GetStocksRequest;
import com.lazarev.grpc.stubs.Stock;
import com.lazarev.grpc.stubs.StockSenderGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Random;

@GrpcService
public class StockSenderService extends StockSenderGrpc.StockSenderImplBase {
    @Override
    public void getStocks(GetStocksRequest request, StreamObserver<Stock> responseObserver) {
        for(int i=0; i<request.getNumber(); i++) {
            Stock stock = Stock.newBuilder()
                    .setName("Tesla")
                    .setMoney(buildMoney())
                    .build();
            responseObserver.onNext(stock);
        }
        responseObserver.onCompleted();
    }

    private Money buildMoney() {
        Random random = new Random();
        return Money.newBuilder()
                .setCurrencyCode("USD")
                .setUnits(random.nextInt(5, 21))
                .setNanos(random.nextInt(0, 1_00_000_000))
                .build();
    }
}
