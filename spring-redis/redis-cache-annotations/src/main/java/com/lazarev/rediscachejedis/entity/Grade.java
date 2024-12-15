package com.lazarev.rediscachejedis.entity;

import com.lazarev.rediscachejedis.enums.EmployeeGrade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ordinal;

    @Enumerated(EnumType.STRING)
    private EmployeeGrade name;

    private Integer salaryFrom;

    private Integer salaryTo;

    private Integer requiredExperienceInYears;
}
