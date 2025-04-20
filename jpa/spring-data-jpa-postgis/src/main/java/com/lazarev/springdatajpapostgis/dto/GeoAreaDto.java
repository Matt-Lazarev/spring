package com.lazarev.springdatajpapostgis.dto;

public record GeoAreaDto(
        double latitude,
        double longitude,
        double radius
) { }
