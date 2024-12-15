package com.lazarev.springsoapservercxf.service;

import com.lazarev.springsoapservercxf.model.CountryRequest;
import com.lazarev.springsoapservercxf.model.CountryResponse;
import com.lazarev.springsoapservercxf.repository.CountryRepository;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@WebService(endpointInterface = "com.lazarev.springsoapservercxf.service.CountryWebService")
public class CountryWebServiceImpl implements CountryWebService {
    private final CountryRepository countryRepository;

    public CountryResponse getCountry(CountryRequest request) {
        CountryResponse response = new CountryResponse();
        response.setCountry(countryRepository.findCountryByName(request.getName()));

        return response;
    }
}
