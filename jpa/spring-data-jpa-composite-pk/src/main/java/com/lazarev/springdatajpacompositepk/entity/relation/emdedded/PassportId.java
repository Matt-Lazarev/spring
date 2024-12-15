package com.lazarev.springdatajpacompositepk.entity.relation.emdedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PassportId implements Serializable {
    private String number;
    private String series;
}
