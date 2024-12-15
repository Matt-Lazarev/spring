package com.lazarev.springrest.dto;

import java.util.Set;

public record StudentDto(Integer id, String name, String group, Set<CourseDto> courses) {
}
