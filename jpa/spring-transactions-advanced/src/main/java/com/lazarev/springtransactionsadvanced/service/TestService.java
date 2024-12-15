package com.lazarev.springtransactionsadvanced.service;

import com.lazarev.springtransactionsadvanced.entity.postgres.ProductP;
import com.lazarev.springtransactionsadvanced.repository.postgres.ProductPRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.security.Escape;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TransactionTemplate transactionTemplate;
    private final ProductPRepository productPRepository;

    public void test() {
        transactionTemplate.execute(status -> {
            try{
                productPRepository.updateAmountByName("Test", 10);
            }
            catch (RuntimeException ex){
                status.setRollbackOnly();
            }
            return null;
        });
    }
}
