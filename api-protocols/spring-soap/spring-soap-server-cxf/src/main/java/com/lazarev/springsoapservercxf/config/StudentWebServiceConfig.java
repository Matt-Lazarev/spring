package com.lazarev.springsoapservercxf.config;

import com.lazarev.springsoapservercxf.service.StudentWebService;
import jakarta.xml.ws.Endpoint;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StudentWebServiceConfig {
    private final Bus bus;
    private final StudentWebService studentWebService;

    @Bean
    public Endpoint studentEndpoint() {
        Endpoint endpoint = new EndpointImpl(bus, studentWebService);
        endpoint.publish("/student");
        return endpoint;
    }
}
