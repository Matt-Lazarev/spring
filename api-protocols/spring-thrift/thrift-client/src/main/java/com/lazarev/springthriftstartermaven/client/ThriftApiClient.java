package com.lazarev.springthriftstartermaven.client;

import com.lazarev.thrift.service.calculator.CalculatorResult;
import com.lazarev.thrift.service.calculator.CalculatorService;
import info.developerblog.spring.thrift.annotation.ThriftClient;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ThriftApiClient {
    @ThriftClient(serviceId = "calculator-service")
    private CalculatorService.Client client;

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        try {
            CalculatorResult addResult = client.add(10, 20);
            System.out.println(addResult);

            CalculatorResult divResult = client.div(15, 0);
            System.out.println(divResult);

            CalculatorResult digitResult = client.digits(123);
            System.out.println(digitResult);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
