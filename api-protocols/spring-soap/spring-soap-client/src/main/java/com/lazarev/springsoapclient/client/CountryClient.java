package com.lazarev.springsoapclient.client;

import com.lazarev.spring.soap.GetCountryRequest;
import com.lazarev.spring.soap.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import static com.lazarev.springsoapclient.config.WebServiceConfig.COUNTRIES_NAMESPACE_URI;

@Slf4j
public class CountryClient extends WebServiceGatewaySupport {

    public GetCountryResponse getCountry(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting location for " + country);

        return (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/countries", request,
                        new SoapActionCallback(COUNTRIES_NAMESPACE_URI + "/GetCountryRequest")
                );
    }
}
