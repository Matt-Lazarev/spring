package com.lazarev.springsoapservercxf.service;

import com.lazarev.springsoapservercxf.model.CountryRequest;
import com.lazarev.springsoapservercxf.model.CountryResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface CountryWebService {

    @WebMethod
    CountryResponse getCountry(CountryRequest request);
}