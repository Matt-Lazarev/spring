package com.lazarev.rediscachejedis.dto.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazarev.rediscachejedis.dto.grade.GradeDto;
import com.lazarev.rediscachejedis.dto.project.ProjectDto;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record EmployeeInfoDto(
        Integer id,
        String firstname,
        String lastname,
        Integer salary,
        GradeDto grade,
        Set<ProjectDto> projects
) { }
