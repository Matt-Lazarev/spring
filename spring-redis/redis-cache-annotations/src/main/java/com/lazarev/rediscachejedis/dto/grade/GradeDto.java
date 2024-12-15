package com.lazarev.rediscachejedis.dto.grade;

public record GradeDto(
        Integer id,
        Integer ordinal,
        String name,
        Integer salaryFrom,
        Integer salaryTo,
        Integer requiredExperienceInYears
) { }
