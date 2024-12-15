package com.lazarev.springsoap.service;

import com.lazarev.soap.countries_web_service.GetCountryRequest;
import com.lazarev.soap.countries_web_service.GetCountryResponse;
import com.lazarev.springsoap.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public GetCountryResponse getCountry(GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountryByName(request.getName()));

        return response;
    }
}
