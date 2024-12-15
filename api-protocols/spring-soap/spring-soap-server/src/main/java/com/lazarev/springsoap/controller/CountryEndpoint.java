package com.lazarev.springsoap.controller;

import com.lazarev.soap.countries_web_service.GetCountryRequest;
import com.lazarev.soap.countries_web_service.GetCountryResponse;
import com.lazarev.springsoap.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.lazarev.springsoap.config.WebServiceConfig.COUNTRIES_NAMESPACE_URI;

@Endpoint
@RequiredArgsConstructor
public class CountryEndpoint {
    private final CountryService countryService;

    @ResponsePayload
    @PayloadRoot(namespace = COUNTRIES_NAMESPACE_URI, localPart = "getCountryRequest")
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        return countryService.getCountry(request);
    }
}
