package com.lazarev.springdatajpacompositepk.entity.idclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyId implements Serializable {
    private Integer companyId;
    private Integer systemId;
}
