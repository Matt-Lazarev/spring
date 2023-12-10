package com.lazarev.springdatajpacompositepk.entity.relation.emdedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "passport")
public class Passport {
    @EmbeddedId
    private PassportId passportId;

    private String country;
}
