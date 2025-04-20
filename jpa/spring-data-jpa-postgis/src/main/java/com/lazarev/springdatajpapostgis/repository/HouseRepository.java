package com.lazarev.springdatajpapostgis.repository;

import com.lazarev.springdatajpapostgis.entity.House;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long> {

    @Query("select h from House h where ST_Distance(h.location, :point) < :radius")
    List<House> findNearbyHouses(Point point, double radius);
}
