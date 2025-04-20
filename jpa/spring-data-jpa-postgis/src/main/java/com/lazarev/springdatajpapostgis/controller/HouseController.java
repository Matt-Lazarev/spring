package com.lazarev.springdatajpapostgis.controller;

import com.lazarev.springdatajpapostgis.dto.GeoAreaDto;
import com.lazarev.springdatajpapostgis.dto.LocationDto;
import com.lazarev.springdatajpapostgis.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/houses")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;

    @GetMapping("/nearby")
    public List<LocationDto> findNearbyHouses(GeoAreaDto geoAreaDto) {
        return houseService.findNearby(geoAreaDto);
    }

    @PostMapping
    public void saveHouse(@RequestBody LocationDto dto) {
        houseService.saveHouse(dto);
    }
}
