package com.lazarev.springdatajpapostgis.dto;

public record LocationDto(
        String address,
        double longitude,
        double latitude
) { }

