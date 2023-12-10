package com.lazarev.springdatajpa.entity.composite;

import com.lazarev.springdatajpa.entity.composite.CompanyId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "companies")
@IdClass(CompanyId.class)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_gen")
    @SequenceGenerator(name = "company_id_gen", sequenceName = "company_id_seq")
    private Integer companyId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_id_gen")
    @SequenceGenerator(name = "system_id_gen", sequenceName = "system_id_seq")
    private Integer systemId;

    private String name;
}
