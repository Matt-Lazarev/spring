package com.lazarev.springsoapservercxf.config;

import com.lazarev.springsoapservercxf.service.CountryWebService;
import jakarta.xml.ws.Endpoint;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CountryWebServiceConfig {
    private final Bus bus;
    private final CountryWebService countryWebService;

    @Bean
    public Endpoint countryEndpoint() {
        Endpoint endpoint = new EndpointImpl(bus, countryWebService);
        endpoint.publish("/country");
        return endpoint;
    }
}
