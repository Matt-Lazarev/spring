package com.lazarev.springtransactionsadvanced.controller;

import com.lazarev.springtransactionsadvanced.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public void testTransactionTemplate(){
        testService.test();
    }
}
