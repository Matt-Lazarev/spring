package com.lazarev.springthriftstartermaven.client;

import com.lazarev.thrift.service.calculator.CalculatorResult;
import com.lazarev.thrift.service.calculator.CalculatorService;
import com.lazarev.thrift.service.samples.Person;
import com.lazarev.thrift.service.samples.TPersonInfo;
import info.developerblog.spring.thrift.annotation.ThriftClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ThriftApiClient {
    @ThriftClient(serviceId = "calculator-service")
    private CalculatorService.Client calculatorServiceClient;

    @ThriftClient(serviceId = "person-info-service")
    private TPersonInfo.Client personInfoClient;

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        invokeCalculatorService();
        invokePersonInfoService();
    }

    private void invokeCalculatorService() {
        try {
            CalculatorResult addResult = calculatorServiceClient.add(10, 20);
            System.out.println(addResult);

            CalculatorResult divResult = calculatorServiceClient.div(15, 1);
            System.out.println(divResult);

            CalculatorResult digitResult = calculatorServiceClient.digits(123);
            System.out.println(digitResult);
        }
        catch (Exception ex) {
            log.warn("Thrift error: ", ex);
        }
    }

    private void invokePersonInfoService() {
        try {
            Person person = new Person();
            person.setName("Mike");
            person.setValue(30);
            person.setAgreement(true);
            personInfoClient.sendInfo(person);
        }
        catch (Exception ex) {
            log.warn("Thrift error: ", ex);
        }
    }
}
