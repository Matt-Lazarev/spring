package com.lazarev.springthriftstartermaven.controller;

import com.lazarev.thrift.service.samples.Person;
import com.lazarev.thrift.service.samples.TPersonInfo;
import lombok.RequiredArgsConstructor;
import ru.trylogic.spring.boot.thrift.annotation.ThriftController;

@ThriftController("/api/person")
@RequiredArgsConstructor
public class TPersonInfoController implements TPersonInfo.Iface {
    @Override
    public void sendInfo(Person person) {
        System.out.println(person);
    }
}
