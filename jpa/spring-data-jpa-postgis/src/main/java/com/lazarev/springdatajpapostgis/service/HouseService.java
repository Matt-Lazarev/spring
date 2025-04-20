package com.lazarev.springdatajpapostgis.service;

import com.lazarev.springdatajpapostgis.dto.GeoAreaDto;
import com.lazarev.springdatajpapostgis.dto.LocationDto;
import com.lazarev.springdatajpapostgis.entity.House;
import com.lazarev.springdatajpapostgis.repository.HouseRepository;
import com.lazarev.springdatajpapostgis.util.GeometryUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final GeometryUtil geometryUtil;

    public List<LocationDto> findNearby(GeoAreaDto geoAreaDto) {
        Point point = geometryUtil.createPoint(geoAreaDto.longitude(), geoAreaDto.latitude());
        return houseRepository.findNearbyHouses(point, geoAreaDto.radius())
                .stream()
                .map(house -> new LocationDto(
                        house.getAddress(),
                        house.getLocation().getX(),
                        house.getLocation().getY()))
                .toList();
    }

    public void saveHouse(LocationDto location) {
        House house = new House();
        house.setAddress(location.address());
        house.setLocation(geometryUtil.createPoint(location.longitude(), location.latitude()));
        houseRepository.save(house);
    }
}
