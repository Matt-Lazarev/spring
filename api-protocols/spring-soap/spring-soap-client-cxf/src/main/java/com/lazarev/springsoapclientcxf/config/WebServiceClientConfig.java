package com.lazarev.springsoapclientcxf.config;

import com.lazarev.spring.soap.CountryWebService;
import com.lazarev.spring.soap.CountryWebServiceImplService;
import com.lazarev.spring.soap.StudentWebService;
import com.lazarev.spring.soap.StudentWebServiceImplService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceClientConfig {

    @Bean
    public StudentWebService studentWebServiceClient() {
        return new StudentWebServiceImplService().getStudentWebServiceImplPort();
    }

    @Bean
    public CountryWebService countryWebServiceClient() {
        return new CountryWebServiceImplService().getCountryWebServiceImplPort();
    }
}
